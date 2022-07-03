package com.test.topandroidrepo.data.remote.sources

import com.test.topandroidrepo.data.remote.api.TopRepoApiService
import com.test.utilities.middleware.SafeApiRequest

class TopRepoDataSourceImpl(
    private val apiService: TopRepoApiService
) : SafeApiRequest(), TopRepoDataSource {
    override suspend fun searchRepos(
        query: String, sortBy: String, limit: Int
    ) = apiRequest {
        apiService.searchRepos(query, sortBy, limit)
    }

    override suspend fun getUser(username: String) = apiRequest {
        apiService.getUser(username)
    }
}