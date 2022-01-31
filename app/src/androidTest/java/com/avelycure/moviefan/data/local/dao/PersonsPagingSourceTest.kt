package com.avelycure.moviefan.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.avelycure.moviefan.data.local.AppDatabase
import com.avelycure.moviefan.data.local.entities.EntityMovie
import com.avelycure.moviefan.data.local.entities.EntityPerson
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PersonsPagingSourceTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: CachePersonsDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.cachePopularPersonsDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertPersons() = runBlocking {
        withContext(TestCoroutineDispatcher()) {
            val persons = listOf(
                EntityPerson(
                    1, true, 1, 1, "Acting", "Jason Statham", 100f, "google.com",
                    emptyList(), emptyList()
                ),
                EntityPerson(
                    2, true, 1, 1, "Acting", "Dwayne Johnson", 110f, "google.com",
                    emptyList(), emptyList()
                ),
                EntityPerson(
                    3, true, 1, 1, "Acting", "Ryan Reynolds", 210f, "google.com",
                    emptyList(), emptyList()
                )
            )

            dao.insertPersons(persons)

            val pagingSourceFactory = dao.getPersons()

            val expectedResult = PagingSource.LoadResult.Page(
                data = persons,
                prevKey = null,
                nextKey = null,
                itemsBefore = 0,
                itemsAfter = 0
            )

            val result = pagingSourceFactory.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            Truth.assertThat(result).isEqualTo(expectedResult)
        }
    }

    @Test
    fun clearPersonsDatabase() = runBlocking {
        withContext(TestCoroutineDispatcher()){
            dao.clearPersons()
            val pagingSourceFactory = dao.getPersons()

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

            Truth.assertThat(result).isEqualTo(expectedResult)
        }
    }

}