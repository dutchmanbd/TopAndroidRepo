package com.test.topandroidrepo.data.model

import com.google.gson.annotations.SerializedName

data class OwnerDto(
    @SerializedName("login")
    val username: String,
    @SerializedName("url")
    val url: String?,
)