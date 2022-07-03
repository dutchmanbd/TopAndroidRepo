package com.test.topandroidrepo.domain.db.dao


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.test.topandroidrepo.RepoList
import com.test.topandroidrepo.domain.db.TopRepoDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class RepoDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    /**
     * I have no enough time to complete all test using hilt
     */

    @Inject
    @Named("test_db")
    lateinit var db: TopRepoDatabase
    private lateinit var repoDao: RepoDao

    @Before
    fun setup() {
        hiltRule.inject()
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
        val dbRepos = repoDao.searchReposByStars()

        assertThat(dbRepos).containsExactlyElementsIn(repos)
            .inOrder()
    }


}