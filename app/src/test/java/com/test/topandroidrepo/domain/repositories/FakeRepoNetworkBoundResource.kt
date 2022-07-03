package com.test.topandroidrepo.domain.repositories

import com.test.topandroidrepo.OpenForTesting
import com.test.topandroidrepo.data.mapper.toRepo
import com.test.topandroidrepo.data.model.RepoDto
import com.test.topandroidrepo.domain.model.Repo
import com.test.utilities.middleware.NetworkBoundResource
import com.test.utilities.responses.*

@OpenForTesting
class FakeRepoNetworkBoundResource(
    private val shouldNetworkError: Boolean
) : NetworkBoundResource<List<Repo>, List<RepoDto>>() {

    private val localRepos = mutableListOf<Repo>()

    override fun onFetchFailed() {

    }

    override suspend fun saveCallResult(item: List<RepoDto>) {
        val repos = item.map { it.toRepo() }
        if (repos.isNotEmpty()) {
            localRepos.clear()
            localRepos.addAll(repos)
        }
    }

    override fun shouldFetch(data: List<Repo>?): Boolean {
        return data == null
    }

    override suspend fun loadFromDb(): List<Repo>? = localRepos


    override suspend fun createCall(): ApiResponse<List<RepoDto>> {
        return if (shouldNetworkError) {
            ApiErrorResponse(NETWORK_ERROR, 444)
        } else {
            ApiSuccessResponse(RepoList.repoDtos)
        }
    }

}