package com.avelycure.moviefan.data.local.type_converters

import androidx.room.TypeConverter
import com.avelycure.moviefan.data.remote.dto.details.SpokenLanguages
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterGenreIds {
    @TypeConverter
    fun fromProductionCompanies(value: List<Int>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toProductionCompanies(value: String): List<Int> {
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, type)
    }
}
