package com.example.demomvvm.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.demomvvm.domain.GetQuotesUseCase
import com.example.demomvvm.domain.GetRandomQuoteCase
import com.example.demomvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelTest{

    @RelaxedMockK
    private lateinit var getQuoteUseCase: GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteCase: GetRandomQuoteCase

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuoteUseCase, getRandomQuoteCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all quotes an set the first value` () = runTest {
        //Given
        val quoteList = listOf(Quote("Holi", "Ares"), Quote("Adios", "Ares"))
        coEvery { getQuoteUseCase() } returns quoteList
        //When
        quoteViewModel.onCreate()
        //Then
        assert(quoteViewModel.quoteModel.value == quoteList.first())
    }

    @Test
    fun `when randomQuoteUseCase return a quote set on the livedata`() = runTest {
        //Given
        val quote = Quote("Esto es una prueba", "Ares")
        coEvery {  getRandomQuoteCase() } returns quote
        //When
        quoteViewModel.randomQuote()
        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }

    @Test
    fun `if randomQuoteCase return null keep last value`() = runTest {
        //Given
        val quote = Quote("Esto es una prueba", "Ares")
        quoteViewModel.quoteModel.value = quote
        coEvery {  getRandomQuoteCase() } returns null
        //When
        quoteViewModel.randomQuote()
        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }

}