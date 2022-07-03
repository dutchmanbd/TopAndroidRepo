package com.test.topandroidrepo.data.mapper

import com.test.topandroidrepo.data.model.OwnerDto
import com.test.topandroidrepo.domain.model.Owner

fun OwnerDto.toOwner() = Owner(
    username = username,
    url = url ?: ""
)