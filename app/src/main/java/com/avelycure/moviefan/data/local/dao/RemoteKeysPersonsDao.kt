package com.avelycure.moviefan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avelycure.moviefan.data.local.entities.EntityRemoteKeysPersons

@Dao
interface RemoteKeysPersonsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entityRemoteKey: List<EntityRemoteKeysPersons>)

    @Query("SELECT * FROM remote_keys_persons WHERE movieId = :movieId")
    suspend fun remoteKeysPersonsId(movieId: Int): EntityRemoteKeysPersons?

    @Query("DELETE FROM remote_keys_persons")
    suspend fun clearRemoteKeys()
}