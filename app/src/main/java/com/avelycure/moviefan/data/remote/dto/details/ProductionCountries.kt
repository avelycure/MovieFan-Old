package com.avelycure.moviefan.data.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountries(
    val iso_3166_1:String,
    val name: String
)
