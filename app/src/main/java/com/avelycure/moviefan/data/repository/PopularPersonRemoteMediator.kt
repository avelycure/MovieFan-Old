package com.avelycure.moviefan.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.avelycure.moviefan.data.local.AppDatabase
import com.avelycure.moviefan.data.local.entities.EntityPopularPerson
import com.avelycure.moviefan.data.local.entities.EntityRemoteKeysPopularPersons
import com.avelycure.moviefan.data.remote.dto.search_person.toEntityPopularPerson
import com.avelycure.moviefan.data.remote.service.IPostsService
import io.ktor.utils.io.errors.*

private const val TMDB_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class PopularPersonRemoteMediator(
    private val postsService: IPostsService,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, EntityPopularPerson>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EntityPopularPerson>
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
            val apiResponse = postsService.getPopularPerson(page)

            val persons = apiResponse.results
            val endOfPaginationReached = persons.isEmpty()

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.remoteKeysPopularPersonsDao().clearRemoteKeys()
                    appDatabase.cachePopularPersonsDao().clearPopularPersons()
                }
                val prevKey = if (page == TMDB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = persons.map {
                    EntityRemoteKeysPopularPersons(
                        movieId = it.id,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                appDatabase.remoteKeysPopularPersonsDao().insertAll(keys)
                appDatabase.cachePopularPersonsDao().insertPopularPersons(persons.map {
                    it.toEntityPopularPerson()
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

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, EntityPopularPerson>): EntityRemoteKeysPopularPersons? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                appDatabase.remoteKeysPopularPersonsDao().remoteKeysPersonsId(movie.personId)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, EntityPopularPerson>): EntityRemoteKeysPopularPersons? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                appDatabase.remoteKeysPopularPersonsDao().remoteKeysPersonsId(movie.personId)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, EntityPopularPerson>
    ): EntityRemoteKeysPopularPersons? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.personId?.let { movieId ->
                appDatabase.remoteKeysPopularPersonsDao().remoteKeysPersonsId(movieId)
            }
        }
    }
}