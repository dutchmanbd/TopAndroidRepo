package com.test.topandroidrepo.domain.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.test.topandroidrepo.ApiUtils
import com.test.topandroidrepo.MainCoroutineRule
import com.test.topandroidrepo.RepoList
import com.test.topandroidrepo.data.remote.sources.TopRepoDataSource
import com.test.topandroidrepo.data.repositories.TopRepositoryImpl
import com.test.topandroidrepo.domain.db.TopRepoDatabase
import com.test.topandroidrepo.domain.db.dao.RepoDao
import com.test.topandroidrepo.domain.model.Repo
import com.test.topandroidrepo.mock
import com.test.utilities.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


@ExperimentalCoroutinesApi
class TopRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: TopRepository
    private val repoDao = mock(RepoDao::class.java)
    private val dataSource = mock(TopRepoDataSource::class.java)


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val db = mock(TopRepoDatabase::class.java)

        `when`(db.repoDao()).thenReturn(repoDao)
        repository = TopRepositoryImpl(dataSource, repoDao)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `search repos from server insert in database`() = runTest {
        val dbRepos = RepoList.repos
        val observer = mock<Observer<Resource<List<Repo>>>>()
        val call = ApiUtils.successCall(RepoList.createSearchRepoResponse())
        `when`(repoDao.searchReposByStars()).thenReturn(listOf())
        `when`(dataSource.searchRepos(anyString(), anyString(), anyInt())).thenReturn(call)
        val reposLive = repository.searchRepos(
            "Android",
            "stars",
            50
        )

        reposLive.observeForever(observer)
        verify(repoDao).insertRepos(dbRepos)

    }
}