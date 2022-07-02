package com.test.topandroidrepo.presentation.fragments.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.test.topandroidrepo.MainCoroutineRule
import com.test.topandroidrepo.domain.repository.FakeTopRepository
import com.test.utilities.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class RepoListViewModelTest {

    private val testDispatcher = StandardTestDispatcher(
        TestCoroutineScheduler()
    )
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: FakeTopRepository
    private lateinit var viewModel: RepoListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeTopRepository()
        viewModel = RepoListViewModel(repository)
    }

    @After
    fun teardown(){
        Dispatchers.resetMain()
    }


    @Test
    fun `get repos, return repo list`() = runTest {
        viewModel.getRepos().observeForever {
            assertThat(it).isInstanceOf(Resource.Success::class.java)
        }
    }

    @Test
    fun `get repos, return network error`()  = runTest {
        repository.shouldNetworkError = true
        viewModel.getRepos().observeForever {
            assertThat(it).isInstanceOf(Resource.Failure::class.java)
        }
    }

}