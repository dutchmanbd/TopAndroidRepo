package com.test.utilities.middleware

import com.test.utilities.responses.ApiResponse
import com.test.utilities.responses.JSON_PARSE_ERROR
import com.test.utilities.responses.NETWORK_ERROR
import com.test.utilities.responses.UNKNOWN_ERROR
import org.json.JSONException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException



abstract class SafeApiRequest {
    suspend fun <R : Any> apiRequest(call: suspend () -> Response<R>): ApiResponse<R> {
        return try {
            val response = call.invoke()
            ApiResponse.create(response)
        } catch (e: JSONException) {
            ApiResponse.create(JSON_PARSE_ERROR)
        } catch (e: IOException) {
            ApiResponse.create(NETWORK_ERROR)
        } catch (e: SocketTimeoutException){
            ApiResponse.create(NETWORK_ERROR)
        } catch (e: Exception) {
            val message = e.message ?: UNKNOWN_ERROR
            val index = message.indexOf(":")
            val formattedMessage = if(index > 0){
                message.substring(index)
            } else {
                message
            }
            ApiResponse.create( formattedMessage)
        }

    }

}