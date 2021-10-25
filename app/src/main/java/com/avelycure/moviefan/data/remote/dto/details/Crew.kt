package com.avelycure.moviefan.data.remote.dto.details

data class Crew(
    val adult: Boolean,
    val gender: Int?,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Float,
    val profile_path: String?,
    val credit_id: String,
    val department: String,
    val job: String
)
