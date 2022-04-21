package com.example.demomvvm.domain

import com.example.demomvvm.data.QuoteRepository
import com.example.demomvvm.data.model.QuoteModel
import com.example.demomvvm.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteCase @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): Quote? {
        var quotes = repository.getAllQuotesFromDatabase()
        if (!quotes.isNullOrEmpty()) {
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }
}