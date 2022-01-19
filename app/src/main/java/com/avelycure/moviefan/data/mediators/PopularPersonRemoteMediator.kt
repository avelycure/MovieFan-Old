package com.avelycure.moviefan.data.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.avelycure.moviefan.data.local.AppDatabase
import com.avelycure.moviefan.data.local.entities.EntityPerson
import com.avelycure.moviefan.data.local.entities.EntityRemoteKeysPersons
import com.avelycure.moviefan.data.remote.mappers.*
import com.avelycure.moviefan.data.remote.service.persons.popular.IPopularPersonsService
import io.ktor.utils.io.errors.*


@OptIn(ExperimentalPagingApi::class)
class PopularPersonRemoteMediator(
    private val popularPersonsService: IPopularPersonsService,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, EntityPerson>() {
    private val TMDB_STARTING_PAGE_INDEX = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EntityPerson>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
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
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }
        }

        try {
            val apiResponse = popularPersonsService.getPopularPerson(page)

            val persons = apiResponse.results
            val endOfPaginationReached = persons.isEmpty()

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.remoteKeysPopularPersonsDao().clearRemoteKeys()
                    appDatabase.cachePopularPersonsDao().clearPersons()
                }
                val prevKey = if (page == TMDB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = persons.map {
                    EntityRemoteKeysPersons(
                        movieId = it.id,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                appDatabase.remoteKeysPopularPersonsDao().insertAll(keys)
                appDatabase.cachePopularPersonsDao().insertPersons(persons.map {
                    it.toEntityPopularPerson()
                })
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, EntityPerson>): EntityRemoteKeysPersons? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                appDatabase.remoteKeysPopularPersonsDao().remoteKeysPersonsId(movie.personId)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, EntityPerson>): EntityRemoteKeysPersons? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                appDatabase.remoteKeysPopularPersonsDao().remoteKeysPersonsId(movie.personId)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, EntityPerson>
    ): EntityRemoteKeysPersons? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.personId?.let { movieId ->
                appDatabase.remoteKeysPopularPersonsDao().remoteKeysPersonsId(movieId)
            }
        }
    }
}