package com.avelycure.moviefan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avelycure.moviefan.data.local.entities.EntityRemoteKeysPopularPersons

@Dao
interface RemoteKeysPopularPersonsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entityRemoteKey: List<EntityRemoteKeysPopularPersons>)

    @Query("SELECT * FROM remote_keys WHERE movieId = :movieId")
    suspend fun remoteKeysPersonsId(movieId: Int) : EntityRemoteKeysPopularPersons?

    @Query("DELETE FROM remote_keys_popular_persons")
    suspend fun clearRemoteKeys()
}