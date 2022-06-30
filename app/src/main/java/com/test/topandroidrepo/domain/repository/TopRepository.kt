package com.test.topandroidrepo.domain.repository

import androidx.lifecycle.LiveData
import com.test.topandroidrepo.domain.model.Repo
import com.test.utilities.Resource

interface TopRepository {

    fun getRepos(queryMap: Map<String, String>): LiveData<Resource<List<Repo>>>

}