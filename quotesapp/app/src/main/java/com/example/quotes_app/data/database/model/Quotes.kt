package com.example.quotes_app.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quotes_table")
data class Quotes(
    @ColumnInfo(name = "phrase")
    var quotes_phrase: String? = null,
    @ColumnInfo(name = "person")
    var quotes_person: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var quotes_id: Int? = null
}