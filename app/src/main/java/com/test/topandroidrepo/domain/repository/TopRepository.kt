package com.test.topandroidrepo.domain.repository

import androidx.lifecycle.LiveData
import com.test.topandroidrepo.domain.model.Repo
import com.test.topandroidrepo.domain.model.User
import com.test.utilities.Resource

interface TopRepository {

    fun searchRepos(queryMap: Map<String, String>): LiveData<Resource<List<Repo>>>

    fun getUser(username: String): LiveData<Resource<User>>
}