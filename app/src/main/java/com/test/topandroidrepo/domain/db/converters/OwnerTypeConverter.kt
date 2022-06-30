package com.test.topandroidrepo.domain.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.test.topandroidrepo.domain.model.Owner

object OwnerTypeConverter {

    @JvmStatic
    @TypeConverter
    fun stringFromOwner(value: String): Owner? {
        return if (value.isEmpty()) null else Gson().fromJson(value, Owner::class.java)
    }

    @JvmStatic
    @TypeConverter
    fun ownerToString(owner: Owner?): String {
        return if (owner == null) "" else Gson().toJson(owner)
    }
}