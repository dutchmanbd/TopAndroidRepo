package com.test.topandroidrepo.data.remote.api

import com.test.topandroidrepo.data.remote.responses.SearchRepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TopRepoApiService {

    @GET("search/repositories")
    suspend fun getRepositories(
        @QueryMap queryMap: Map<String, String>
    ): Response<SearchRepositoryResponse>


}