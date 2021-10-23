package com.avelycure.moviefan.data.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguages(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)
