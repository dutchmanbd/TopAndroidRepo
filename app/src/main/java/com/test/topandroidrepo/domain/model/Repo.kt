package com.test.topandroidrepo.domain.model


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import kotlinx.parcelize.Parcelize
import java.util.*


@Entity(
    tableName = "repos",
    indices = [
        Index("id"),
        Index("owner_username")],
    primaryKeys = ["name", "owner_username"]
)
@Parcelize
data class Repo(
    val id: Int,
    val nodeId: String,
    val name: String,
    val fullName: String,
    @field:Embedded(prefix = "owner_")
    val owner: Owner,
    val description: String,
    val createdAt: Date,
    val updated: Date,
    val pushedAt: Date,
    val stars: Int,
    val topics: List<String>
) : Parcelable {

}