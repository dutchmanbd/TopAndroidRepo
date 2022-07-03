package com.test.topandroidrepo.domain.repositories

import androidx.lifecycle.LiveData
import com.test.topandroidrepo.OpenForTesting
import com.test.topandroidrepo.domain.model.Repo
import com.test.utilities.Resource

@OpenForTesting
class FakeTopRepository : TopRepository {

    var shouldNetworkError = false

    override fun searchRepos(
        queryMap: Map<String, String>
    ): LiveData<Resource<List<Repo>>> =
        FakeRepoNetworkBoundResource(shouldNetworkError).asLiveData()



}