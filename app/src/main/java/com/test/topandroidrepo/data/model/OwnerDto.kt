package com.test.topandroidrepo.data.model

import com.google.gson.annotations.SerializedName

data class OwnerDto(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)