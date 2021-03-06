package com.test.topandroidrepo

import com.test.extensions.time.date
import com.test.topandroidrepo.data.model.OwnerDto
import com.test.topandroidrepo.data.model.RepoDto
import com.test.topandroidrepo.data.remote.responses.SearchRepositoryResponse
import com.test.topandroidrepo.domain.model.Owner
import com.test.topandroidrepo.domain.model.Repo

object RepoList {

    val repoDtos = listOf(
        RepoDto(
            id = 82128465, nodeId = "MDEwOlJlcG9zaXRvcnk4MjEyODQ2NQ==",
            name = "Android", fullName = "open-android/Android",
            owner = OwnerDto(
                username = "open-android",
                url = ""
            ),
            description = "GitHub上最火的Android开源项目,所有开源项目都有详细资料和配套视频",
            createdAt = "2017-02-16T02:10:13Z",
            updatedAt = "2022-07-01T11:19:34Z",
            pushedAt = "2022-06-16T04:59:05Z",
            topics = listOf(
                "android",
                "java"
            ),
            stargazersCount = 10
        ),
        RepoDto(
            id = 12544093, nodeId = "MDEwOlJlcG9zaXRvcnkxMjU0NDA5Mw==",
            name = "Android", fullName = "hmkcode/Android",
            owner = OwnerDto(
                username = "hmkcode",
                url = ""
            ),
            description = "Android related examples",
            createdAt = "2013-09-02T16:12:28Z",
            updatedAt = "2022-07-01T05:15:47Z",
            pushedAt = "2022-05-26T07:20:22Z",
            topics = listOf(),
            stargazersCount = 10
        )
    )

    val repos = listOf(
        Repo(
            id = 82128465, nodeId = "MDEwOlJlcG9zaXRvcnk4MjEyODQ2NQ==",
            name = "Android", fullName = "open-android/Android",
            owner = Owner(
                username = "open-android",
                url = ""
            ),
            description = "GitHub上最火的Android开源项目,所有开源项目都有详细资料和配套视频",
            createdAt = "2017-02-16T02:10:13Z".date(),
            updated = "2022-07-01T11:19:34Z".date(),
            pushedAt = "2022-06-16T04:59:05Z".date(),
            topics = listOf(
                "android",
                "java"
            ),
            stars = 10
        ),
        Repo(
            id = 12544093, nodeId = "MDEwOlJlcG9zaXRvcnkxMjU0NDA5Mw==",
            name = "Android", fullName = "hmkcode/Android",
            owner = Owner(
                username = "hmkcode",
                url = ""
            ),
            description = "Android related examples",
            createdAt = "2013-09-02T16:12:28Z".date(),
            updated = "2022-07-01T05:15:47Z".date(),
            pushedAt = "2022-05-26T07:20:22Z".date(),
            topics = listOf(),
            stars = 10
        )
    )


    fun createSearchRepoResponse(): SearchRepositoryResponse {
        return SearchRepositoryResponse(
            1337219,
            false,
            repoDtos
        )
    }

}