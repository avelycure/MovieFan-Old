package com.avelycure.moviefan.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.avelycure.moviefan.data.local.AppDatabase
import com.avelycure.moviefan.data.local.dao.CacheMovieInfoDao
import com.avelycure.moviefan.data.local.entities.EntityMovieInfo
import com.avelycure.moviefan.data.local.entities.EntityPopularMovie
import com.avelycure.moviefan.data.remote.dto.details.DetailResponse
import com.avelycure.moviefan.data.remote.dto.person.ResponsePersonImages
import com.avelycure.moviefan.data.remote.dto.video.VideosResponse
import com.avelycure.moviefan.data.remote.service.IPostsService
import com.avelycure.moviefan.data.remote.sources.SearchPagingSource
import com.avelycure.moviefan.domain.mappers.toEntityMovieInfo
import com.avelycure.moviefan.domain.models.MovieInfo

class MovieRepository(
    private val postsService: IPostsService,
    private val cacheMovieInfoDao: CacheMovieInfoDao,
    private val database: AppDatabase
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }

    // Returns Pager for fetching popular movies
    fun getPagerWithRemoteMediator(
        pagingConfig: PagingConfig = getDefaultPageConfig()
    ): Pager<Int, EntityPopularMovie> {
        val pagingSourceFactory = { database.cachePopularMovieDao().getPopularMovies() }
        return Pager(
            config = pagingConfig,
            remoteMediator = PopularMovieRemoteMediator(
                postsService = postsService,
                appDatabase = database
            ),
            pagingSourceFactory = pagingSourceFactory
        )
    }

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
        PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true, prefetchDistance = 60,
            initialLoadSize = 60
        )

    suspend fun saveMovieInfo(movieInfo: MovieInfo) {
        cacheMovieInfoDao.insertMovie(movieInfo.toEntityMovieInfo())
    }

    suspend fun getMovieInfoFromCache(id: Int): EntityMovieInfo {
        return cacheMovieInfoDao.getMovieInfo(id)
    }

    suspend fun getTrailerCode(id: Int): VideosResponse {
        return postsService.getVideos(id)
    }

    suspend fun getDetails(id: Int): DetailResponse {
        return postsService.getMovieDetail(id)
    }

    suspend fun getPersonImages(id: Int): ResponsePersonImages{
        return postsService.getPersonImages(id)
    }
}