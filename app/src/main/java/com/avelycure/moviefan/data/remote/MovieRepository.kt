package com.avelycure.moviefan.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.data.remote.dto.details.mappers.toMovieInfo
import com.avelycure.moviefan.data.remote.dto.video.mappers.toVideoInfo
import com.avelycure.moviefan.domain.MovieInfo
import com.avelycure.moviefan.domain.PopularMovie
import com.avelycure.moviefan.domain.VideoInfo
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val postsService: IPostsService
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }

    fun letMovieFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<PopularMovie>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { MoviePagingSource(postsService = postsService) }
        ).flow

    private fun getDefaultPageConfig() = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)


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
}