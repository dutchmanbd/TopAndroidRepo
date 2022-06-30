package com.test.topandroidrepo.data.remote.sources

import com.test.topandroidrepo.data.remote.responses.SearchRepositoryResponse
import com.test.utilities.responses.ApiResponse


interface TopRepoDataSource {

    suspend fun getRepositories(
        queryMap: Map<String, String>
    ): ApiResponse<SearchRepositoryResponse>

}