package com.test.topandroidrepo.presentation.fragments.repos


import com.test.topandroidrepo.domain.repository.TopRepository
import com.test.topandroidrepo.presentation.activities.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val repository: TopRepository
) : BaseViewModel() {




    fun getTopRepos() = repository.getRepos(
        mapOf(
            "q" to QUERY,
            "per_page" to LIMIT.toString()
        )
    )

    companion object {
        private const val QUERY = "Android"
        private const val LIMIT = 50
    }

}