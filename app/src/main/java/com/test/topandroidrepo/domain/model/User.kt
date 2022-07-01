package com.test.topandroidrepo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val avatarUrl: String,
    val createdAt: String,
    val id: Int,
    @PrimaryKey(autoGenerate = false)
    val username: String,
    val name: String,
    val nodeId: String,
    val type: String,
    val updatedAt: String,
    val url: String
)