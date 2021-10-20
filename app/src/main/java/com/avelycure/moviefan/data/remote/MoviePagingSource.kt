package com.avelycure.moviefan.data.remote

import androidx.paging.PagingSource
import com.avelycure.moviefan.data.remote.dto.toPopularMovie
import com.avelycure.moviefan.domain.PopularMovie

class MoviePagingSource(
    val postsService: PostsService
) : PagingSource<Int, PopularMovie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovie> {
        try {
            val page = params.key ?: 1
            val response = postsService
                .getPosts(page)
                .results
                .map {
                    it.toPopularMovie()
                }

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