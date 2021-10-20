package com.avelycure.moviefan.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.avelycure.moviefan.domain.PopularMovie
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    val postsService: PostsService
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }

    fun letMovieFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<PopularMovie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { MoviePagingSource(postsService = postsService) }
        ).flow
    }

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }
}