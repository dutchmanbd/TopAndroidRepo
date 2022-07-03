package com.test.topandroidrepo.data.mapper

import com.test.extensions.time.date
import com.test.topandroidrepo.data.model.RepoDto
import com.test.topandroidrepo.domain.model.Repo

fun RepoDto.toRepo() = Repo(
    id = id, nodeId = nodeId,
    name = name, fullName = fullName,
    owner = owner.toOwner(),
    description = description ?: "",
    createdAt = createdAt.date(),
    updated = updatedAt.date(),
    pushedAt = pushedAt.date(),
    stars = stargazersCount,
    topics = topics
)