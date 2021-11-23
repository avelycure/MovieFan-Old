package com.avelycure.moviefan.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avelycure.moviefan.data.local.entities.EntityMovie
import com.avelycure.moviefan.domain.models.Movie

@Dao
interface CacheDao {
    @Insert
    fun insertMovie(movieInfo: EntityMovie)

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getMovieInfo(movieId: Int):EntityMovie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movies: List<Movie>)

    @Query("SELECT * FROM popular_movies")
    fun getPopularMovies(): PagingSource<Int, Movie>

    @Query("DELETE FROM popular_movies")
    suspend fun clearPopularMovies()
}