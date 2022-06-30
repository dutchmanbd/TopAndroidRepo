package com.test.topandroidrepo.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.topandroidrepo.domain.db.TOP_REPO_DATABASE
import com.test.topandroidrepo.domain.db.TopRepoDatabase
import com.test.topandroidrepo.domain.db.dao.RepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideTopRepoDatabase(
        @ApplicationContext context: Context
    ): TopRepoDatabase = Room.databaseBuilder(
        context, TopRepoDatabase::class.java,
        TOP_REPO_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideRepoDao(
        db: TopRepoDatabase
    ): RepoDao = db.repoDao()

}