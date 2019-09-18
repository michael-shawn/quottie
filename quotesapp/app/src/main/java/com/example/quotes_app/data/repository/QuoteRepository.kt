package com.example.quotes_app.data.repository

import androidx.lifecycle.LiveData
import com.example.quotes_app.data.database.model.Quotes

interface QuoteRepository {

    fun insertQuote(quotes: Quotes)

    suspend fun readQuotes(): LiveData<List<Quotes>>

    suspend fun  readQuoteDetail(id: Int): LiveData<Quotes>

    fun deleteQuote(id: Int)

    fun updateQuote(id: Int, quotablePhrase: String, quotedBy: String)
}