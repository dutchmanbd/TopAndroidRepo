package com.test.topandroidrepo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class Repo(
    val id: Int,
    @PrimaryKey(autoGenerate = false)
    val nodeId: String,
    val name: String,
    val fullName: String,
    val owner: Owner?,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
    val pushedAt: String,
    val topics: List<String>
)