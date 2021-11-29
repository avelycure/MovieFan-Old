package com.avelycure.moviefan.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avelycure.moviefan.data.local.entities.EntityMovie

@Dao
interface CacheMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<EntityMovie>)

    @Query("SELECT * FROM movies")
    fun getMovies(): PagingSource<Int, EntityMovie>

    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}