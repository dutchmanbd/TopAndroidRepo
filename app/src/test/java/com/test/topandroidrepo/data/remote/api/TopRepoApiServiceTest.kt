package com.test.topandroidrepo.data.remote.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TopRepoApiServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiService: TopRepoApiService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createApiService(){
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TopRepoApiService::class.java)
    }

    @After
    fun stopService(){
        mockWebServer.shutdown()
    }



}