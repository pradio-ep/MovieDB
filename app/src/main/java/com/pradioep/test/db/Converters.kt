package com.pradioep.test.db

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import com.pradioep.test.model.*
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


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

    @TypeConverter
    fun storedStringToGenres(data: String?): List<Genres?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Genres?>?>() {}.type
        return gson.fromJson<List<Genres?>>(data, listType)
    }

    @TypeConverter
    fun genresToStoredString(myObjects: List<Genres?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    @TypeConverter
    fun storedStringToProductionCompanies(data: String?): List<ProductionCompanies?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<ProductionCompanies?>?>() {}.type
        return gson.fromJson<List<ProductionCompanies?>>(data, listType)
    }

    @TypeConverter
    fun productionCompaniesToStoredString(myObjects: List<ProductionCompanies?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    @TypeConverter
    fun storedStringToProductionCountries(data: String?): List<ProductionCountries?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<ProductionCountries?>?>() {}.type
        return gson.fromJson<List<ProductionCountries?>>(data, listType)
    }

    @TypeConverter
    fun productionCountriesToStoredString(myObjects: List<ProductionCountries?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    @TypeConverter
    fun storedStringToSpokenLanguages(data: String?): List<SpokenLanguages?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<SpokenLanguages?>?>() {}.type
        return gson.fromJson<List<SpokenLanguages?>>(data, listType)
    }

    @TypeConverter
    fun spokenLanguagesToStoredString(myObjects: List<SpokenLanguages?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}