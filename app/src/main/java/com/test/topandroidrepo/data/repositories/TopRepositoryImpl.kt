package com.test.topandroidrepo.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.test.topandroidrepo.data.mapper.toRepo
import com.test.topandroidrepo.data.remote.responses.SearchRepositoryResponse
import com.test.topandroidrepo.data.remote.sources.TopRepoDataSource
import com.test.topandroidrepo.domain.db.dao.RepoDao
import com.test.topandroidrepo.domain.model.Repo
import com.test.topandroidrepo.domain.repositories.TopRepository
import com.test.utilities.RateLimiter
import com.test.utilities.Resource
import com.test.utilities.middleware.NetworkBoundResource
import java.util.concurrent.TimeUnit


class TopRepositoryImpl(
    private val dataSource: TopRepoDataSource,
    private val repoDao: RepoDao
) : TopRepository {

    private val rateLimit = RateLimiter<String>(5, TimeUnit.MINUTES)

    override fun searchRepos(
        query: String, sortBy: String, limit: Int
    ): LiveData<Resource<List<Repo>>> =
        object : NetworkBoundResource<List<Repo>, SearchRepositoryResponse>() {
            override suspend fun saveCallResult(item: SearchRepositoryResponse) {
                val repos = item.repos?.mapIndexed { _, repoDto -> repoDto.toRepo() }
                    ?: emptyList()
                if (repos.isNotEmpty()) {
                    repoDao.insertRepos(repos)
                }
            }

            override fun shouldFetch(data: List<Repo>?): Boolean {
                if (data == null || data.isEmpty())
                    return true
                return rateLimit.shouldFetch(
                    "$query$sortBy$limit"
                )
            }

            override suspend fun loadFromDb(): List<Repo>? {
                Log.e("Repository", "loadFromDb: $sortBy")
                return if (sortBy == "stars") repoDao.searchReposByStars() else repoDao.searchReposByUpdated()
            }

            override suspend fun createCall() = dataSource.searchRepos(query, sortBy, limit)

            override fun onFetchFailed() {
                rateLimit.reset("$query$sortBy$limit")
            }
        }.asLiveData()


}