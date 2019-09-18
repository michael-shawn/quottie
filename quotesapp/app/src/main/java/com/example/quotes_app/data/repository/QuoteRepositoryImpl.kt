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

    override fun insertQuote(quotes: Quotes) {
        GlobalScope.launch(Dispatchers.IO) {
            quotesDatabase.quotesDao().insertQuote(quotes)
        }
    }

    override suspend fun readQuotes(): LiveData<List<Quotes>> {
        return withContext(Dispatchers.IO) {
            return@withContext quotesDatabase.quotesDao().readQuotes()
        }
    }

    override suspend fun readQuoteDetail(id: Int): LiveData<Quotes> {
        return withContext(Dispatchers.IO) {
            return@withContext quotesDatabase.quotesDao().readQuoteDetail(id)
        }
    }

    override fun deleteQuote(id: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            quotesDatabase.quotesDao().deleteQuote(id)
        }
    }

    override fun updateQuote(id: Int, quotablePhrase: String, quotedBy: String) {
        GlobalScope.launch(Dispatchers.IO) {
            quotesDatabase.quotesDao().updateQuote(id, quotablePhrase, quotedBy)
        }
    }
}
