package com.test.topandroidrepo.presentation.fragments.detail

import com.test.topandroidrepo.domain.repository.TopRepository
import com.test.topandroidrepo.presentation.activities.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: TopRepository
) : BaseViewModel() {

    fun getUser(username: String?) = repository.getUser(username ?: "")
}