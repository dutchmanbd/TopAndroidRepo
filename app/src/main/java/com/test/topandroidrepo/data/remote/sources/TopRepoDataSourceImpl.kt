package com.test.topandroidrepo.data.remote.sources

import com.test.topandroidrepo.data.remote.api.TopRepoApiService
import com.test.utilities.middleware.SafeApiRequest

class TopRepoDataSourceImpl(
    private val apiService: TopRepoApiService
) : SafeApiRequest(), TopRepoDataSource {
    override suspend fun searchRepos(queryMap: Map<String, String>) = apiRequest {
        apiService.searchRepos(queryMap)
    }

    override suspend fun getUser(username: String) = apiRequest {
        apiService.getUser(username)
    }
}