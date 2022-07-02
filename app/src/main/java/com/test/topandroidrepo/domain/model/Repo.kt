package com.test.topandroidrepo.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "repos")
@Parcelize
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
    val stargazersCount: Int,
    val topics: List<String>
) : Parcelable