package com.test.topandroidrepo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    val username: String,
    val url: String
): Parcelable