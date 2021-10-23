package com.avelycure.moviefan.data.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompanies(
    val name: String,
    val id: Int,
    val logo_path: String?,
    val origin_country: String
)
