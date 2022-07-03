package com.test.topandroidrepo


import com.test.utilities.responses.ApiResponse
import retrofit2.Response

object ApiUtils {

    fun <T : Any> successCall(data: T) = createCall(Response.success(data))

    fun <T : Any> createCall(
        response: Response<T>
    ) = ApiResponse.create(response)

}