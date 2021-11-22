package com.avelycure.moviefan.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.avelycure.moviefan.data.local.dao.CacheDao
import com.avelycure.moviefan.data.local.entities.EntityMovie
import com.avelycure.moviefan.data.local.entities.toMovieInfo
import com.avelycure.moviefan.data.remote.dto.details.DetailResponse
import com.avelycure.moviefan.data.remote.dto.video.VideosResponse
import com.avelycure.moviefan.data.remote.service.IPostsService
import com.avelycure.moviefan.data.remote.sources.PopularPagingSource
import com.avelycure.moviefan.data.remote.sources.SearchPagingSource
import com.avelycure.moviefan.domain.models.MovieInfo
import com.avelycure.moviefan.domain.models.toEntityMovie

class MovieRepository(
    private val postsService: IPostsService,
    private val cacheDao: CacheDao
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }

    // Returns Pager for fetching popular movies
    fun getPopularPager(pagingConfig: PagingConfig = getDefaultPageConfig()) =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { PopularPagingSource(postsService = postsService) }
        )

    // Returns Pager for fetching movies by their title
    fun getSearchPager(query: String, pagingConfig: PagingConfig = getDefaultPageConfig()) =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                SearchPagingSource(
                    postsService = postsService,
                    query = query
                )
            }
        )

    private fun getDefaultPageConfig() =
        PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)

    suspend fun getTrailerCode(id: Int): VideosResponse {
        return postsService.getVideos(id)
    }

    suspend fun getDetails(id: Int): DetailResponse {
        return postsService.getMovieDetail(id)
    }

    suspend fun saveMovieInfo(movieInfo: MovieInfo) {
        cacheDao.insertMovie(movieInfo.toEntityMovie())
    }

    suspend fun getMovieInfoFromCache(id: Int): EntityMovie {
        return cacheDao.getMovieInfo(id)
    }
}