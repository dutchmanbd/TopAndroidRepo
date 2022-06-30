package com.test.topandroidrepo.data.mapper

import com.test.topandroidrepo.data.model.RepoDto
import com.test.topandroidrepo.domain.model.Repo

fun RepoDto.toRepo() = Repo(
    id = id, nodeId = nodeId, name = name, fullName = fullName,
    owner = owner?.toOwner(), isPrivate = isPrivate,
    description = description ?: "",
    isFork = isFork, url = url, createdAt = createdAt,
    updatedAt = updatedAt, pushedAt = pushedAt, size = size,
    stargazersCount = stargazersCount, watchersCount = watchersCount,
    forksCount = forksCount, openIssuesCount = openIssuesCount,
    score = score, forksUrl = forksUrl, allowForking = allowForking,
    archived = archived, hasIssues = hasIssues,
    openIssues = openIssues, topics = topics,
    visibility = visibility, watchers = watchers
)