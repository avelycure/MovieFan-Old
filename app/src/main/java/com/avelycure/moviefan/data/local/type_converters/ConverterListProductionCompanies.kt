package com.avelycure.moviefan.data.local.type_converters

import androidx.room.TypeConverter
import com.avelycure.moviefan.data.remote.dto.details.ProductionCompanies
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterListProductionCompanies {
    @TypeConverter
    fun fromProductionCompanies(value: List<ProductionCompanies>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCompanies>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toProductionCompanies(value: String): List<ProductionCompanies> {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCompanies>>() {}.type
        return gson.fromJson(value, type)
    }
}