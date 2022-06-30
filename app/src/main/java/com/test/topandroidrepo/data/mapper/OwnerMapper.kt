package com.test.topandroidrepo.data.mapper

import com.test.topandroidrepo.data.model.OwnerDto
import com.test.topandroidrepo.domain.model.Owner

fun OwnerDto.toOwner() = Owner(
    avatarUrl = avatarUrl,
    id = id,
    login = login,
    nodeId = nodeId,
    reposUrl = reposUrl,
    type = type,
    url = url
)