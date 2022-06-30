package com.test.topandroidrepo.di

import android.content.Context
import com.google.gson.Gson
import com.test.topandroidrepo.BuildConfig
import com.test.topandroidrepo.data.remote.api.TopRepoApiService
import com.test.topandroidrepo.data.remote.middleware.AuthInterceptorImpl
import com.test.topandroidrepo.data.remote.middleware.ConnectivityInterceptorImpl
import com.test.topandroidrepo.data.remote.sources.TopRepoDataSource
import com.test.topandroidrepo.data.remote.sources.TopRepoDataSourceImpl
import com.test.topandroidrepo.data.repository.TopRepositoryImpl
import com.test.topandroidrepo.domain.db.dao.RepoDao
import com.test.topandroidrepo.domain.repository.TopRepository
import com.test.utilities.middleware.AuthInterceptor
import com.test.utilities.middleware.ConnectivityInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideAuthInterceptor(
    ): AuthInterceptor = AuthInterceptorImpl()

    @Provides
    @Singleton
    fun provideConnectivityInterceptor(
        @ApplicationContext context: Context
    ): ConnectivityInterceptor = ConnectivityInterceptorImpl(context)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        connectivityInterceptor: ConnectivityInterceptor,
        authInterceptor: AuthInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(connectivityInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideTopRepoApiService(
        okHttpClient: OkHttpClient,
        gson: Gson,
    ): TopRepoApiService = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(TopRepoApiService::class.java)

    @Provides
    @Singleton
    fun provideTopRepoDataSource(
        apiService: TopRepoApiService
    ): TopRepoDataSource = TopRepoDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideTopRepository(
        dataSource: TopRepoDataSource,
        repoDao: RepoDao
    ): TopRepository = TopRepositoryImpl(dataSource, repoDao)

}