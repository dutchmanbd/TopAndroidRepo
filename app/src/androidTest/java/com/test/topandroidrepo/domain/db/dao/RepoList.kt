package com.test.topandroidrepo.domain.db.dao

import com.test.topandroidrepo.domain.model.Owner
import com.test.topandroidrepo.domain.model.Repo

object RepoList {

    //    val id: Int,
//    val nodeId: String,
//    val name: String,
//    val fullName: String,
//    val owner: Owner?,
//    val description: String,
//    val createdAt: String,
//    val updatedAt: String,
//    val pushedAt: String,
//    val topics: List<String>
    val repos = listOf(
        Repo(
            id = 82128465, nodeId = "MDEwOlJlcG9zaXRvcnk4MjEyODQ2NQ==",
            name = "Android", fullName = "open-android/Android",
            owner = Owner(
                username = "open-android"
            ),
            description = "GitHub上最火的Android开源项目,所有开源项目都有详细资料和配套视频",
            createdAt = "2017-02-16T02:10:13Z",
            updatedAt = "2022-07-01T11:19:34Z",
            pushedAt = "2022-06-16T04:59:05Z",
            topics = listOf(
                "android",
                "java"
            )
        ),
        Repo(
            id = 12544093, nodeId = "MDEwOlJlcG9zaXRvcnkxMjU0NDA5Mw==",
            name = "Android", fullName = "hmkcode/Android",
            owner = Owner(
                username = "hmkcode"
            ),
            description = "Android related examples",
            createdAt = "2013-09-02T16:12:28Z",
            updatedAt = "2022-07-01T05:15:47Z",
            pushedAt = "2022-05-26T07:20:22Z",
            topics = listOf()
        )
    )
}