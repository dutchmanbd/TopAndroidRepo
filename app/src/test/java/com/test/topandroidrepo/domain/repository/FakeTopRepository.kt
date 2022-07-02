package com.test.topandroidrepo.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.test.topandroidrepo.OpenForTesting
import com.test.topandroidrepo.domain.model.Repo
import com.test.topandroidrepo.domain.model.User
import com.test.utilities.Resource

@OpenForTesting
class FakeTopRepository : TopRepository {

    var shouldNetworkError = false

    override fun searchRepos(
        queryMap: Map<String, String>
    ): LiveData<Resource<List<Repo>>> =
        FakeRepoNetworkBoundResource(shouldNetworkError).asLiveData()

    override fun getUser(username: String): LiveData<Resource<User>> = liveData {
        emit(
            Resource.Success(
                User(
                    "", "", 1, "", "",
                    "", "", "", ""
                )
            )
        )
    }


}