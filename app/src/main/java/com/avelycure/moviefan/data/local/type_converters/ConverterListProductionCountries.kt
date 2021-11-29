package com.avelycure.moviefan.data.local.type_converters

import androidx.room.TypeConverter
import com.avelycure.moviefan.data.remote.dto.details.ProductionCountries
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterProductionCountries {
    @TypeConverter
    fun fromProductionCompanies(value: List<ProductionCountries>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCountries>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toProductionCompanies(value: String): List<ProductionCountries> {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCountries>>() {}.type
        return gson.fromJson(value, type)
    }
}