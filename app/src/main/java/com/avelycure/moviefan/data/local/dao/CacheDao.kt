package com.avelycure.moviefan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.avelycure.moviefan.data.local.entities.EntityMovie

@Dao
interface CacheDao {
    @Insert
    fun insertMovie(movieInfo: EntityMovie)
}