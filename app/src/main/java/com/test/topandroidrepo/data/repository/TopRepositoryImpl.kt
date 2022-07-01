package com.test.topandroidrepo.data.repository

import androidx.lifecycle.LiveData
import com.test.topandroidrepo.data.mapper.toRepo
import com.test.topandroidrepo.data.mapper.toUser
import com.test.topandroidrepo.data.model.UserDto
import com.test.topandroidrepo.data.remote.responses.SearchRepositoryResponse
import com.test.topandroidrepo.data.remote.sources.TopRepoDataSource
import com.test.topandroidrepo.domain.db.dao.RepoDao
import com.test.topandroidrepo.domain.db.dao.UserDao
import com.test.topandroidrepo.domain.model.Repo
import com.test.topandroidrepo.domain.model.User
import com.test.topandroidrepo.domain.repository.TopRepository
import com.test.utilities.RateLimiter
import com.test.utilities.Resource
import com.test.utilities.middleware.NetworkBoundResource
import java.util.concurrent.TimeUnit

class TopRepositoryImpl(
    private val dataSource: TopRepoDataSource,
    private val repoDao: RepoDao,
    private val userDao: UserDao
) : TopRepository {

    private val rateLimit = RateLimiter<String>(5, TimeUnit.MINUTES)

    override fun getRepos(
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
            override suspend fun createCall() = dataSource.getRepositories(queryMap)

            override fun onFetchFailed() {
                rateLimit.reset(queryMap.values.joinToString(","))
            }
        }.asLiveData()

//    override fun getUser(
//        username: String
//    ): LiveData<Resource<User>> = object : NetworkBoundResource<User, UserDto>() {
//        override suspend fun saveCallResult(item: UserDto) {
//            val user = item.toUser()
//            userDao.insertUser(user)
//        }
//
//        override fun shouldFetch(data: User?): Boolean {
//            return data == null || rateLimit.shouldFetch(username)
//        }
//
//        override suspend fun loadFromDb() = userDao.getUser(username)
//        override suspend fun createCall() = dataSource.getUser(username)
//        override fun onFetchFailed() {
//            rateLimit.reset(username)
//        }
//    }.asLiveData()
}