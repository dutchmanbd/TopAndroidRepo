package com.test.utilities.responses

import com.google.gson.annotations.SerializedName

data class ResponseDTO<E>(
    @SerializedName("output")
    val dto: E?,
)