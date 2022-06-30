package com.test.topandroidrepo.data.model

import com.google.gson.annotations.SerializedName

data class RepoDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("owner")
    val owner: OwnerDto?,
    @SerializedName("private")
    val isPrivate: Boolean,
    @SerializedName("description")
    val description: String?,
    @SerializedName("fork")
    val isFork: Boolean,
    @SerializedName("url")
    val url: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("pushed_at")
    val pushedAt: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int,
    @SerializedName("score")
    val score: Double,
    @SerializedName("forks_url")
    val forksUrl: String,
    @SerializedName("allow_forking")
    val allowForking: Boolean,
    @SerializedName("archived")
    val archived: Boolean,
    @SerializedName("has_issues")
    val hasIssues: Boolean,
    @SerializedName("open_issues")
    val openIssues: Int,
    @SerializedName("topics")
    val topics: List<String>,
    @SerializedName("visibility")
    val visibility: String,
    @SerializedName("watchers")
    val watchers: Int
)