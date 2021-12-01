package com.avelycure.moviefan.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avelycure.moviefan.data.local.entities.EntityPerson

@Dao
interface CachePersonsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersons(movies: List<EntityPerson>)

    @Query("SELECT * FROM persons")
    fun getPersons(): PagingSource<Int, EntityPerson>

    @Query("DELETE FROM persons")
    suspend fun clearPersons()
}