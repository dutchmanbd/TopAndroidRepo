package com.test.topandroidrepo.domain.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.test.extensions.gson.fromJson


object TopicTypeConverter {
    @TypeConverter
    @JvmStatic
    fun listStringToString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @JvmStatic
    @TypeConverter
    fun stringFromListString(value: String): List<String> {
        return Gson().fromJson(value)
    }

}