package com.avelycure.moviefan.data.remote.sources

import android.util.Log
import androidx.paging.PagingSource
import com.avelycure.moviefan.data.remote.dto.search_person.ResultPerson
import com.avelycure.moviefan.data.remote.service.persons.search.ISearchPersonsService

class SearchPersonPagingSource(
    val personsService: ISearchPersonsService,
    val query: String
) : PagingSource<Int, ResultPerson>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultPerson> {
        try {
            val page = params.key ?: 1
            val response = personsService
                .getPersonsByName(query, page)
                .results

            return LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            Log.d("mytag", "Error in source: ${e.message}")
            return LoadResult.Error(e)
        }
    }
}