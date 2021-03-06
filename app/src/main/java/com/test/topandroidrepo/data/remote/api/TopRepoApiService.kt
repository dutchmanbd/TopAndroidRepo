package com.test.topandroidrepo.data.remote.api

import com.test.topandroidrepo.data.model.UserDto
import com.test.topandroidrepo.data.remote.responses.SearchRepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TopRepoApiService {

    @GET("search/repositories")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("sort") sortBy: String,
        @Query("per_page") limit: Int
    ): Response<SearchRepositoryResponse>

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): Response<UserDto>


}