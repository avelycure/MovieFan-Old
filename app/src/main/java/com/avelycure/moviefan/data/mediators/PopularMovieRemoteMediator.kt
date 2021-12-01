package com.avelycure.moviefan.data.mediators

import android.util.Log
import androidx.paging.*
import androidx.room.withTransaction
import coil.network.HttpException
import com.avelycure.moviefan.data.local.AppDatabase
import com.avelycure.moviefan.data.local.entities.EntityMovie
import com.avelycure.moviefan.data.local.entities.EntityRemoteKeysMovies
import com.avelycure.moviefan.data.remote.mappers.toEntityPopularMovie
import com.avelycure.moviefan.data.remote.service.movies.popular.IPopularMoviesService
import io.ktor.utils.io.errors.*


@OptIn(ExperimentalPagingApi::class)
class PopularMovieRemoteMediator(
    private val popularMoviesService: IPopularMoviesService,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, EntityMovie>() {
    private val TMDB_STARTING_PAGE_INDEX = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EntityMovie>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                Log.d("mytag", "Refresh")
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: TMDB_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
            }
            LoadType.APPEND -> {
                Log.d("mytag", "Prepend")
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }
        }

        try {
            val apiResponse = popularMoviesService.getPopularMovies(page)

            val movies = apiResponse.results
            val endOfPaginationReached = movies.isEmpty()

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.remoteKeysDao().clearRemoteKeys()
                    appDatabase.cachePopularMovieDao().clearMovies()
                }
                val prevKey = if (page == TMDB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = movies.map {
                    EntityRemoteKeysMovies(movieId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                appDatabase.remoteKeysDao().insertAll(keys)
                appDatabase.cachePopularMovieDao().insertMovies(movies.map {
                    it.toEntityPopularMovie()
                })
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, EntityMovie>): EntityRemoteKeysMovies? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                appDatabase.remoteKeysDao().remoteKeysMovieId(movie.movieId)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, EntityMovie>): EntityRemoteKeysMovies? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                appDatabase.remoteKeysDao().remoteKeysMovieId(movie.movieId)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, EntityMovie>
    ): EntityRemoteKeysMovies? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.movieId?.let { movieId ->
                appDatabase.remoteKeysDao().remoteKeysMovieId(movieId)
            }
        }
    }
}