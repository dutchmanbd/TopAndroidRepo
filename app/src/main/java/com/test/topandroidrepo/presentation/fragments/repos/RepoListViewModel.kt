package com.test.topandroidrepo.presentation.fragments.repos


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.test.topandroidrepo.domain.model.Repo
import com.test.topandroidrepo.domain.repositories.TopRepository
import com.test.topandroidrepo.presentation.activities.base.BaseViewModel
import com.test.utilities.AbsentLiveData
import com.test.utilities.FilterItem
import com.test.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val repository: TopRepository
) : BaseViewModel() {

    private val _searchState = MutableLiveData(
        SearchRepoState()
    )

    fun updateQuery(query: String) {
        _searchState.value = _searchState.value?.copy(
            search = query
        )
    }

    fun updateFilter(filter: FilterItem) {
        _searchState.value = _searchState.value?.copy(
            filter = filter.filter
        )
    }

    val repos: LiveData<Resource<List<Repo>>> = _searchState.switchMap { state ->
        if (state.search.isBlank()) {
            AbsentLiveData.create()
        } else {
            repository.searchRepos(
                state.search,
                state.filter.ifEmpty { FilterItem.Stars.filter },
                LIMIT
            )
        }
    }


    companion object {
        private const val LIMIT = 50
    }

}