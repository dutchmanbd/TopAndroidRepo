package com.test.topandroidrepo.data.remote.middleware


import com.test.utilities.middleware.AuthInterceptor
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptorImpl : AuthInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authenticationRequest = request(originalRequest)
        return chain.proceed(authenticationRequest)
    }

    private fun request(originalRequest: Request): Request {

        return originalRequest.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
    }
}