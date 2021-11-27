package com.avelycure.moviefan.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avelycure.moviefan.data.local.entities.EntityPopularPerson

@Dao
interface CachePopularPersonsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularPersons(movies: List<EntityPopularPerson>)

    @Query("SELECT * FROM popular_person")
    fun getPopularPersons(): PagingSource<Int, EntityPopularPerson>

    @Query("DELETE FROM popular_person")
    suspend fun clearPopularPersons()
}