package com.example.quotes_app.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quotes_app.data.database.model.Quotes

@Dao
interface QuotesDao {

    @Insert
    fun insertQuote(quote: Quotes)

    @Query("select * from quotes_table order by id desc")
    fun readQuotes(): LiveData<List<Quotes>>

    @Query("select * from quotes_table where id = :id")
    fun readQuoteDetail(id: Int): LiveData<Quotes>

    @Query("delete from quotes_table where id = :id")
    fun deleteQuote(id: Int)

    /*@Delete
    fun deleteQuote(quote: Quotes)*/
}