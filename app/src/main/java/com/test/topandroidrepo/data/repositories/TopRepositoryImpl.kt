package com.test.topandroidrepo.data.repositories

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

    private val rateLimit = RateLimiter<String>(15, TimeUnit.MINUTES)

    override fun searchRepos(
        queryMap: Map<String, String>
    ): LiveData<Resource<List<Repo>>> =
        object : NetworkBoundResource<List<Repo>, SearchRepositoryResponse>() {
            override suspend fun saveCallResult(item: SearchRepositoryResponse) {
                val repos = item.repos?.map { it.toRepo() } ?: emptyList()
                if (repos.isNotEmpty()) {
                    repoDao.insertRepos(repos)
                }
            }

            override fun shouldFetch(data: List<Repo>?): Boolean {
                return data == null || rateLimit.shouldFetch(queryMap.values.joinToString(","))
            }

            override suspend fun loadFromDb() = repoDao.getRepos()
            override suspend fun createCall() = dataSource.searchRepos(queryMap)

            override fun onFetchFailed() {
                rateLimit.reset(queryMap.values.joinToString(","))
            }
        }.asLiveData()


}