package com.avelycure.moviefan.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys_popular_persons")
data class EntityRemoteKeysPopularPersons(
    @PrimaryKey
    val movieId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
