package com.test.topandroidrepo.presentation.fragments.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.test.topandroidrepo.MainCoroutineRule
import com.test.topandroidrepo.domain.model.Repo
import com.test.topandroidrepo.domain.repository.FakeTopRepository
import com.test.topandroidrepo.mock
import com.test.utilities.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@ExperimentalCoroutinesApi
class RepoListViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val repository = mock(FakeTopRepository::class.java)
    private lateinit var viewModel: RepoListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = RepoListViewModel(repository)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `get repos, return repo list`() {
        val result = mock<Observer<Resource<List<Repo>>>>()
        viewModel.repos.observeForever(result)
        viewModel.updateQuery("Android")
        verify(repository).searchRepos(
            mapOf(
                "q" to "Android", "sort" to "stars", "per_page" to "50"
            )
        )
    }

}