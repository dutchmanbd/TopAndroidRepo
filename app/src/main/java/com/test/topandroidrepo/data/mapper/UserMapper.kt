package com.test.topandroidrepo.data.mapper

import com.test.topandroidrepo.data.model.UserDto
import com.test.topandroidrepo.domain.model.User

fun UserDto.toUser() = User(
    avatarUrl = avatarUrl,
    createdAt = createdAt,
    id = id,
    username = username,
    name = name ?: "",
    nodeId = nodeId,
    type = type,
    updatedAt = updatedAt,
    url = url ?: ""
)