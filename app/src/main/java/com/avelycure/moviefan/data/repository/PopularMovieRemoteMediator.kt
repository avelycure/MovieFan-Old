package com.avelycure.moviefan.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import coil.network.HttpException
import com.avelycure.moviefan.data.local.dao.CacheDao
import com.avelycure.moviefan.data.local.entities.EntityPopularMovie
import com.avelycure.moviefan.data.remote.service.IPostsService
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PopularMovieRemoteMediator(
    private val postsService: IPostsService,
    private val cacheDao: CacheDao
) : RemoteMediator<Int, EntityPopularMovie>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EntityPopularMovie>
    ): MediatorResult {
        try {
            Log.d("mytag", "in mediator")
            return MediatorResult.Success(endOfPaginationReached = true)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }
}