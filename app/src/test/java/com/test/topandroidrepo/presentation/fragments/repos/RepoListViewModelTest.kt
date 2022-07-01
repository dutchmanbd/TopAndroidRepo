package com.test.topandroidrepo.presentation.fragments.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.google.common.truth.Truth.assertThat
import com.test.topandroidrepo.MainCoroutineRule
import com.test.topandroidrepo.domain.repository.FakeTopRepository
import com.test.utilities.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RepoListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: RepoListViewModel
    private lateinit var repository: FakeTopRepository

    @Before
    fun setup() {
        repository = FakeTopRepository()
        viewModel = RepoListViewModel(repository)
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