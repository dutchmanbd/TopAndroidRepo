package com.test.topandroidrepo.domain.model

data class Owner(
    val avatarUrl: String,
    val id: Int,
    val login: String,
    val nodeId: String,
    val reposUrl: String,
    val type: String,
    val url: String
)