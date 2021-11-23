package com.avelycure.moviefan.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.avelycure.moviefan.data.local.AppDatabase
import com.avelycure.moviefan.data.local.entities.EntityPopularMovie
import com.avelycure.moviefan.data.local.entities.RemoteKeys
import com.avelycure.moviefan.data.remote.dto.movie.toEntityPopularMovie
import com.avelycure.moviefan.data.remote.service.IPostsService
import com.avelycure.moviefan.domain.models.Movie
import io.ktor.utils.io.errors.*

private const val TMDB_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class PopularMovieRemoteMediator(
    private val postsService: IPostsService,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, EntityPopularMovie>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EntityPopularMovie>
    ): MediatorResult {
        Log.d("mytag", "in mediator")
        val page = when (loadType) {
            LoadType.REFRESH -> {
                Log.d("mytag", "type refresh")
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: TMDB_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                Log.d("mytag", "type prepend")
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
            }
            LoadType.APPEND -> {
                Log.d("mytag", "type append")
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }
        }

        try {
            Log.d("mytag", "page: $page")
            val apiResponse = postsService.getPopularMovies(page)

            val movies = apiResponse.results
            val endOfPaginationReached = movies.isEmpty()

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.remoteKeysDao().clearRemoteKeys()
                    appDatabase.cacheDao().clearPopularMovies()
                }
                val prevKey = if (page == TMDB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = movies.map {
                    RemoteKeys(movieId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                appDatabase.remoteKeysDao().insertAll(keys)
                appDatabase.cacheDao().insertPopularMovies(movies.map {
                    it.toEntityPopularMovie()
                })
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            Log.d("mytag", "io exception: ${exception.message}")
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            Log.d("mytag", "http exception: ${exception.message}")
            return MediatorResult.Error(exception)
        } catch (exception: Exception) {
            Log.d("mytag", "new exception: ${exception.message}")
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, EntityPopularMovie>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                appDatabase.remoteKeysDao().remoteKeysMovieId(movie.movieId)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, EntityPopularMovie>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                appDatabase.remoteKeysDao().remoteKeysMovieId(movie.movieId)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, EntityPopularMovie>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.movieId?.let { movieId ->
                appDatabase.remoteKeysDao().remoteKeysMovieId(movieId)
            }
        }
    }
}