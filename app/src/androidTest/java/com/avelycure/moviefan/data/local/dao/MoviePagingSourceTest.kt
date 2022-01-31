package com.avelycure.moviefan.data.local.dao

import androidx.paging.PagingSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.avelycure.moviefan.data.local.AppDatabase
import com.avelycure.moviefan.data.local.entities.EntityMovie
import org.junit.After
import org.junit.Before
import org.junit.Test
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MoviePagingSourceTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: CacheMovieDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.cachePopularMovieDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertMovies() = runBlocking {
            withContext(TestCoroutineDispatcher()) {
                val movies = listOf(
                    EntityMovie(
                        1, "Spider-Man", "Spider-Man", "google.com",
                        listOf(1, 2, 3), 100f, 100f, "01.01.2000", 101, 30000
                    ),
                    EntityMovie(
                        2, "Lord of the rings", "Lord of the rings", "yandex.ru",
                        listOf(3, 4, 5), 100f, 100f, "01.01.2005", 202, 60000
                    ),
                    EntityMovie(
                        3, "Hobbit", "Hobbit", "mail.ru",
                        listOf(6, 7, 8), 150f, 150f, "01.01.2010", 303, 50000
                    )
                )

                dao.insertMovies(movies)

                val pagingSourceFactory = dao.getMovies()

                val expectedResult = PagingSource.LoadResult.Page(
                    data = movies,
                    prevKey = null,
                    nextKey = null,
                    itemsBefore = 0,
                    itemsAfter = 0
                )

                val result = pagingSourceFactory.load(PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 3,
                    placeholdersEnabled = false
                ))

                assertThat(result).isEqualTo(expectedResult)
            }
        }


    @Test
    fun clearMoviesDatabase() = runBlocking {
        withContext(TestCoroutineDispatcher()){
            dao.clearMovies()
            val pagingSourceFactory = dao.getMovies()

            val expectedResult = PagingSource.LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null,
                itemsBefore = 0,
                itemsAfter = 0
            )

            val result = pagingSourceFactory.load(PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 3,
                placeholdersEnabled = false
            ))

            assertThat(result).isEqualTo(expectedResult)
        }
    }
}