package com.pradioep.test.db

import androidx.room.TypeConverter


class Converters {
    @TypeConverter
    fun gettingListFromString(genreIds: String): List<Int>? {
        val list: MutableList<Int> = ArrayList()
        val array = genreIds.split(",".toRegex()).toTypedArray()
        for (s in array) {
            if (!s.isEmpty()) {
                list.add(s.toInt())
            }
        }
        return list
    }

    @TypeConverter
    fun writingStringFromList(list: List<Int>): String? {
        var genreIds = ""
        for (i in list) {
            genreIds += ",$i"
        }
        return genreIds
    }
}