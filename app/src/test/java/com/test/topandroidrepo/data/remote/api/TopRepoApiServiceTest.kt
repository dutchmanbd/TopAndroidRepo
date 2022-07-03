package com.test.topandroidrepo.data.remote.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.test.topandroidrepo.data.mapper.toRepo
import com.test.topandroidrepo.data.mapper.toUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@OptIn(ExperimentalCoroutinesApi::class)
class TopRepoApiServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiService: TopRepoApiService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createApiService() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TopRepoApiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `searchRepos from server`() = runTest {
        enqueueResponse("repos.json")
        val repos = (apiService.searchRepos(
            "Android",
            "stars",
            50
        ).body()?.repos?.mapIndexed { _, repoDto -> repoDto.toRepo() } ?: emptyList())
        assertThat(repos.size).isEqualTo(2)
    }

    @Test
    fun `getUser from server return not null`() = runTest {
        enqueueResponse("user.json")
        val user = apiService.getUser("open-android").body()?.toUser()
        assertThat(user).isNotNull()
        assertThat(user?.username).isEqualTo("open-android")
    }


    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }

}