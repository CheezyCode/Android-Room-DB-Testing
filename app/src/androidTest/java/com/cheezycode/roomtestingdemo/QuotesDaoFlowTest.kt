package com.cheezycode.roomtestingdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.*

class QuotesDaoFlowTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var quoteDatabase: QuoteDatabase
    lateinit var quotesDao: QuotesDao

    @Before
    fun setUp(){
        quoteDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuoteDatabase::class.java
        ).allowMainThreadQueries().build()
        quotesDao = quoteDatabase.quoteDao()
    }


    @Test
    fun insertQuote_expectedSingleQuote() = runBlocking{
        val quote = Quote(0, "This is a test quote", "CheezyCode")
        val quote2 = Quote(0, "This is a test quote 2", "CheezyCode")
        quotesDao.insertQuote(quote)
        quotesDao.insertQuote(quote2)

        val result = quotesDao.getQuotes().test {
            val quoteList = awaitItem()
            Assert.assertEquals(2, quoteList.size)
            cancel()
        }

    }


    @After
    fun tearDown(){
        quoteDatabase.close()
    }
}











