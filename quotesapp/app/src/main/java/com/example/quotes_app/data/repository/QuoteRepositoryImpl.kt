package com.example.quotes_app.data.repository

import androidx.lifecycle.LiveData
import com.example.quotes_app.data.database.QuotesDatabase
import com.example.quotes_app.data.database.model.Quotes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuoteRepositoryImpl(
    private val quotesDatabase: QuotesDatabase
) : QuoteRepository {

    override fun insert(quotes: Quotes) {
        GlobalScope.launch(Dispatchers.IO) {
            quotesDatabase.quotesDao().insertQuote(quotes)
        }
    }

    override suspend fun read(): LiveData<List<Quotes>> {
        return withContext(Dispatchers.IO) {
            return@withContext quotesDatabase.quotesDao().readQuotes()
        }
    }

    override suspend fun readDetail(id: Int): LiveData<Quotes> {
        return withContext(Dispatchers.IO) {
            return@withContext quotesDatabase.quotesDao().readQuoteDetail(id)
        }
    }

    override fun delete(id: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            quotesDatabase.quotesDao().deleteQuote(id)
        }
    }
}
