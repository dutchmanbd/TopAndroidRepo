package com.test.topandroidrepo.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.topandroidrepo.domain.db.converters.OwnerTypeConverter
import com.test.topandroidrepo.domain.db.converters.TopicTypeConverter
import com.test.topandroidrepo.domain.db.dao.RepoDao
import com.test.topandroidrepo.domain.db.dao.UserDao
import com.test.topandroidrepo.domain.model.Repo

const val TOP_REPO_DATABASE = "top_repos.db"

@Database(
    entities = [Repo::class],
    version = 1,
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2)
//    ]  We can use auto migration but this is test application that why simply ignore this
)
@TypeConverters(
    OwnerTypeConverter::class,
    TopicTypeConverter::class
)
abstract class TopRepoDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
    abstract fun userDao(): UserDao
}