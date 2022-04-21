package com.example.demomvvm.domain

import com.example.demomvvm.data.QuoteRepository
import com.example.demomvvm.data.database.entities.toDatabase
import com.example.demomvvm.data.model.QuoteModel
import com.example.demomvvm.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: QuoteRepository){

    suspend operator fun invoke( ) : List<Quote>{
        val quotes = repository.getAllQuotesFromApi()
        return if(quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }
    }
}