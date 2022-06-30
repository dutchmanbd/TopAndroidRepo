package com.test.topandroidrepo.data.remote.middleware

import android.content.Context
import com.test.extensions.context.isNetworkAvailable
import com.test.topandroidrepo.R
import com.test.utilities.middleware.ConnectivityInterceptor
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptorImpl(
    context: Context
) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!appContext.isNetworkAvailable()) {
            throw IOException(appContext.getString(R.string.no_internet_connection))
        }
        return chain.proceed(chain.request())
    }

}