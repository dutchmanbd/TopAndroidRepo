package com.test.topandroidrepo.domain.repositories

import androidx.lifecycle.LiveData
import com.test.topandroidrepo.domain.model.Repo
import com.test.utilities.Resource

interface TopRepository {

    fun searchRepos(queryMap: Map<String, String>): LiveData<Resource<List<Repo>>>


}