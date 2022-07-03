package com.test.topandroidrepo.domain.repositories

import androidx.lifecycle.LiveData
import com.test.topandroidrepo.domain.model.Repo
import com.test.utilities.Resource


interface TopRepository {

    fun searchRepos(query: String, sortBy: String, limit: Int): LiveData<Resource<List<Repo>>>


}