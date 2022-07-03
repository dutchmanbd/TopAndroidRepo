package com.test.topandroidrepo.data.repositories

import androidx.lifecycle.LiveData
import com.test.topandroidrepo.data.mapper.toUser
import com.test.topandroidrepo.data.model.UserDto
import com.test.topandroidrepo.data.remote.sources.TopRepoDataSource
import com.test.topandroidrepo.domain.db.dao.UserDao
import com.test.topandroidrepo.domain.model.User
import com.test.topandroidrepo.domain.repositories.UserRepository
import com.test.utilities.RateLimiter
import com.test.utilities.Resource
import com.test.utilities.middleware.NetworkBoundResource
import java.util.concurrent.TimeUnit

class UserRepositoryImpl(
    private val dataSource: TopRepoDataSource,
    private val userDao: UserDao
) : UserRepository {

    private val rateLimit = RateLimiter<String>(5, TimeUnit.MINUTES)


    override fun getUser(
        username: String
    ): LiveData<Resource<User>> = object : NetworkBoundResource<User, UserDto>() {
        override suspend fun saveCallResult(item: UserDto) {
            val user = item.toUser()
            userDao.insertUser(user)
        }

        override fun shouldFetch(data: User?): Boolean {
            return data == null || rateLimit.shouldFetch(username)
        }

        override suspend fun loadFromDb() = userDao.getUser(username)
        override suspend fun createCall() = dataSource.getUser(username)
        override fun onFetchFailed() {
            rateLimit.reset(username)
        }
    }.asLiveData()

}