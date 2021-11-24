package com.avelycure.moviefan.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avelycure.moviefan.data.local.entities.EntityPopularMovie

@Dao
interface CachePopularMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movies: List<EntityPopularMovie>)

    @Query("SELECT * FROM popular_movies")
    fun getPopularMovies(): PagingSource<Int, EntityPopularMovie>

    @Query("DELETE FROM popular_movies")
    suspend fun clearPopularMovies()
}