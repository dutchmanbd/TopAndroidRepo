package com.test.topandroidrepo.data.remote.responses


import com.google.gson.annotations.SerializedName
import com.test.topandroidrepo.data.model.RepoDto

data class SearchRepositoryResponse(
    @SerializedName("total_count")
    val totalCount: Long,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val repos: List<RepoDto>?
)