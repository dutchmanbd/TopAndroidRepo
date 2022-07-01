package com.test.topandroidrepo.domain.db.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.test.topandroidrepo.domain.db.TopRepoDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class RepoDaoTest {

    private lateinit var db: TopRepoDatabase
    private lateinit var repoDao: RepoDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TopRepoDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        repoDao = db.repoDao()
    }

    @After
    fun teardown() {
        db.close()
    }


    @Test
    fun insertRepos() = runTest {
        val repos = RepoList.repos
        repoDao.insertRepos(repos)
        val dbRepos = repoDao.getRepos()

        assertThat(dbRepos).containsExactlyElementsIn(repos)
            .inOrder()
    }


}