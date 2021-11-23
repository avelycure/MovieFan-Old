package com.avelycure.moviefan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.avelycure.moviefan.data.local.entities.EntityMovie

@Dao
interface CacheDao {
    @Insert
    fun insertMovie(movieInfo: EntityMovie)

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getMovieInfo(movieId: Int):EntityMovie
}