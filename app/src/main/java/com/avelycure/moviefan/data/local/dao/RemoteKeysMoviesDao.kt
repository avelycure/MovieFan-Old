package com.avelycure.moviefan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avelycure.moviefan.data.local.entities.EntityRemoteKeysMovies

@Dao
interface RemoteKeysMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entityRemoteKeyMovies: List<EntityRemoteKeysMovies>)

    @Query("SELECT * FROM remote_keys_movies WHERE movieId = :movieId")
    suspend fun remoteKeysMovieId(movieId: Int): EntityRemoteKeysMovies?

    @Query("DELETE FROM remote_keys_movies")
    suspend fun clearRemoteKeys()
}