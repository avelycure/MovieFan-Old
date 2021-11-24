package com.avelycure.moviefan.data.remote.sources

import androidx.paging.PagingSource
import com.avelycure.moviefan.data.remote.dto.movie.MovieListResult
import com.avelycure.moviefan.data.remote.service.IPostsService
import com.avelycure.moviefan.data.remote.dto.movie.mappers.toMovie
import com.avelycure.moviefan.domain.models.Movie

class SearchPagingSource (
    val postsService: IPostsService,
    val query: String
) : PagingSource<Int, MovieListResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListResult> {
        try {
            val page = params.key ?: 1
            val response = postsService
                .getMovies(query, page)
                .results

            return LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}