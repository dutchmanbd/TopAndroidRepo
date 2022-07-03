package com.test.topandroidrepo.di

import android.content.Context
import androidx.room.Room
import com.test.topandroidrepo.domain.db.TopRepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(
        @ApplicationContext context: Context
    ) = Room.inMemoryDatabaseBuilder(context, TopRepoDatabase::class.java)
        .allowMainThreadQueries()
        .build()



}