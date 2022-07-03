package com.test.topandroidrepo.domain.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.topandroidrepo.domain.model.Repo

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repo: Repo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<Repo>)


    @Query("SELECT * FROM repos order by stars DESC LIMIT 50")
    suspend fun searchReposByStars(): List<Repo>?

    @Query("SELECT * FROM repos order by updated DESC LIMIT 50")
    suspend fun searchReposByUpdated(): List<Repo>?


}