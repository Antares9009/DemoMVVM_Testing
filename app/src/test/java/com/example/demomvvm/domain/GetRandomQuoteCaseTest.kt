package com.example.demomvvm.domain

import com.example.demomvvm.data.QuoteRepository
import com.example.demomvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRandomQuoteCaseTes(){

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getRandomQuoteCase: GetRandomQuoteCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomQuoteCase = GetRandomQuoteCase(quoteRepository)
    }

    @Test
    fun `when database is empty then return null` () = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns emptyList()

        //When
        val response = getRandomQuoteCase()

        //Then
        assert(response == null)
    }

    @Test
    fun `when database is not empty then return quote` () = runBlocking {
        //Given
        val quoteList = listOf(Quote("Suscribe", "Antares"))
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns quoteList
        //When
        val response = getRandomQuoteCase()

        //Then
        assert(response == quoteList.first())
    }

}