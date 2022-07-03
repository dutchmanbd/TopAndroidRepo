package com.test.topandroidrepo.data.mapper

import com.test.extensions.time.timeInMillis
import com.test.topandroidrepo.data.model.RepoDto
import com.test.topandroidrepo.domain.model.Repo

fun RepoDto.toRepo() = Repo(
    id = id, nodeId = nodeId,
    name = name, fullName = fullName,
    owner = owner.toOwner(),
    description = description ?: "",
    createdAt = createdAt.timeInMillis(),
    updated = updatedAt.timeInMillis(),
    pushedAt = pushedAt.timeInMillis(),
    stars = stargazersCount,
    topics = topics
)