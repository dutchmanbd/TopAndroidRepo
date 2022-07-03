package com.test.topandroidrepo.presentation.fragments.detail

import com.test.topandroidrepo.domain.repositories.UserRepository
import com.test.topandroidrepo.presentation.activities.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel() {

    fun getUser(username: String?) = repository.getUser(username ?: "")
}