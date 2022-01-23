package com.avelycure.moviefan.data.remote.dto.search_person

import kotlinx.serialization.Serializable

@Serializable
data class ResponseSearchPerson(
    val page: Int = 0,
    val results: List<ResultPerson> = emptyList(),
    val total_results: Int = 0,
    val total_pages: Int = 0
)
