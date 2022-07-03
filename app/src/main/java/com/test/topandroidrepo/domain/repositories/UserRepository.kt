package com.test.topandroidrepo.domain.repositories

import androidx.lifecycle.LiveData
import com.test.topandroidrepo.domain.model.User
import com.test.utilities.Resource

interface UserRepository {

    fun getUser(username: String): LiveData<Resource<User>>

}