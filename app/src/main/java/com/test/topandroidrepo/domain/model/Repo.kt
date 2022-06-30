package com.test.topandroidrepo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class Repo(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val nodeId: String,
    val name: String,
    val fullName: String,
    val owner: Owner?,
    val isPrivate: Boolean,
    val description: String,
    val isFork: Boolean,
    val url: String,
    val createdAt: String,
    val updatedAt: String,
    val pushedAt: String,
    val size: Int,
    val stargazersCount: Int,
    val watchersCount: Int,
    val forksCount: Int,
    val openIssuesCount: Int,
    val score: Double,
    val forksUrl: String,
    val allowForking: Boolean,
    val archived: Boolean,
    val hasIssues: Boolean,
    val openIssues: Int,
    val topics: List<String>,
    val visibility: String,
    val watchers: Int
)