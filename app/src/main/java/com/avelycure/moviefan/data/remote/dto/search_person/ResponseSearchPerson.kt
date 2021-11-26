package com.avelycure.moviefan.data.remote.dto.search_person

import kotlinx.serialization.Serializable

@Serializable
data class ResponseSearchPerson(
    val page: Int,
    val results: List<ResultPerson>,
    val total_results: Int,
    val total_pages: Int
)
