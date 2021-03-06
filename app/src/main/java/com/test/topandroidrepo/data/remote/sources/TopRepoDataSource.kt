package com.test.topandroidrepo.data.remote.sources

import com.test.topandroidrepo.data.model.UserDto
import com.test.topandroidrepo.data.remote.responses.SearchRepositoryResponse
import com.test.utilities.responses.ApiResponse


interface TopRepoDataSource {

    suspend fun searchRepos(
        query: String, sortBy: String, limit: Int
    ): ApiResponse<SearchRepositoryResponse>

    suspend fun getUser(
        username: String
    ): ApiResponse<UserDto>



}