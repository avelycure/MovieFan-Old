package com.avelycure.moviefan.data.local.type_converters

import androidx.room.TypeConverter
import com.avelycure.moviefan.data.remote.dto.details.MovieGenre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterListMovieGenre {
    @TypeConverter
    fun fromMovieGenre(value: List<MovieGenre>): String {
        val gson = Gson()
        val type = object : TypeToken<List<MovieGenre>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toMovieGenre(value: String): List<MovieGenre> {
        val gson = Gson()
        val type = object : TypeToken<List<MovieGenre>>() {}.type
        return gson.fromJson(value, type)
    }
}