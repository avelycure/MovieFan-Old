package com.avelycure.moviefan.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.data.remote.dto.details.mappers.toMovieInfo
import com.avelycure.moviefan.data.remote.dto.video.mappers.toVideoInfo
import com.avelycure.moviefan.data.remote.service.IPostsService
import com.avelycure.moviefan.data.remote.sources.MoviePagingSource
import com.avelycure.moviefan.data.remote.sources.SearchPagingSource
import com.avelycure.moviefan.domain.models.MovieInfo
import com.avelycure.moviefan.domain.models.VideoInfo

class MovieRepository(
    private val postsService: IPostsService
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }

    fun getPager(pagingConfig: PagingConfig = getDefaultPageConfig()) =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { MoviePagingSource(postsService = postsService) }
        )

    private fun getDefaultPageConfig() =
        PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)


    suspend fun getVideos(id: Int): VideoInfo {
        val result = postsService.getVideos(id)
        return if (result.results.isNotEmpty())
            result.results[0].toVideoInfo()
        else
            VideoInfo(Constants.NO_TRAILER_CODE.toString())
    }

    suspend fun getDetails(id: Int): MovieInfo {
        return postsService.getMovieDetail(id).toMovieInfo()
    }

    fun getSearchPager(query: String, pagingConfig: PagingConfig = getDefaultPageConfig()) =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { SearchPagingSource(postsService = postsService,
            query = query) }
        )
}