package com.example.demomvvm.domain.model

import com.example.demomvvm.data.database.entities.QuoteEntity
import com.example.demomvvm.data.model.QuoteModel

data class Quote(val quote: String, val author: String)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote, author)