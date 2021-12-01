package com.avelycure.moviefan.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.avelycure.moviefan.data.local.AppDatabase
import com.avelycure.moviefan.data.local.dao.CacheMovieInfoDao
import com.avelycure.moviefan.data.local.entities.EntityMovieInfo
import com.avelycure.moviefan.data.local.entities.EntityMovie
import com.avelycure.moviefan.data.local.entities.EntityPerson
import com.avelycure.moviefan.data.remote.dto.details.DetailResponse
import com.avelycure.moviefan.data.remote.dto.person.ResponsePersonInfo
import com.avelycure.moviefan.data.remote.dto.person.ResponsePersonImages
import com.avelycure.moviefan.data.remote.dto.video.VideosResponse
import com.avelycure.moviefan.data.remote.service.ServiceFactory
import com.avelycure.moviefan.data.remote.sources.SearchPagingSource
import com.avelycure.moviefan.data.remote.sources.SearchPersonPagingSource
import com.avelycure.moviefan.domain.mappers.toEntityMovieInfo
import com.avelycure.moviefan.domain.models.MovieInfo

class MovieRepository(
    private val cacheMovieInfoDao: CacheMovieInfoDao,
    private val database: AppDatabase,
    private val serviceFactory: ServiceFactory
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }

    // Returns Pager for fetching popular movies
    fun getPagerWithRemoteMediator(
        pagingConfig: PagingConfig = getDefaultPageConfig()
    ): Pager<Int, EntityMovie> {
        val pagingSourceFactory = { database.cachePopularMovieDao().getMovies() }
        return Pager(
            config = pagingConfig,
            remoteMediator = PopularMovieRemoteMediator(
                popularMoviesService = serviceFactory.popularMoviesService,
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
                    searchMoviesService = serviceFactory.searchMoviesService,
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
        return serviceFactory.videosService.getVideos(id)
    }

    suspend fun getDetails(id: Int): DetailResponse {
        return serviceFactory.movieInfoService.getMovieDetail(id)
    }

    suspend fun getPersonInfo(id: Int): ResponsePersonInfo {
        return serviceFactory.personInfoService.getPersonInfo(id)
    }

    suspend fun getPersonImages(id: Int): ResponsePersonImages {
        return serviceFactory.personImagesService.getPersonImages(id)
    }

    fun getSearchPersonPager(query: String, pagingConfig: PagingConfig = getDefaultPageConfig()) =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                SearchPersonPagingSource(
                    personsService = serviceFactory.searchPersonsService,
                    query = query
                )
            }
        )

    fun getPagerWithRemoteMediatorForPopularPersons(
        pagingConfig: PagingConfig = getDefaultPageConfig()
    ): Pager<Int, EntityPerson> {
        val pagingSourceFactory = { database.cachePopularPersonsDao().getPersons() }
        return Pager(
            config = pagingConfig,
            remoteMediator = PopularPersonRemoteMediator(
                popularPersonsService = serviceFactory.popularPersonsService,
                appDatabase = database
            ),
            pagingSourceFactory = pagingSourceFactory
        )
    }
}