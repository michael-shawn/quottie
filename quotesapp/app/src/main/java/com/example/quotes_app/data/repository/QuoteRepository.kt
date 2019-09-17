package com.example.quotes_app.data.repository

import androidx.lifecycle.LiveData
import com.example.quotes_app.data.database.model.Quotes

interface QuoteRepository {

    fun insert(quotes: Quotes)

    suspend fun read(): LiveData<List<Quotes>>

    suspend fun  readDetail(id: Int): LiveData<Quotes>

    fun delete(id: Int)
}