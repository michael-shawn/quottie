package com.example.quotes_app.adapter

import com.example.quotes_app.R
import com.example.quotes_app.data.database.model.Quotes
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.quotes_list_item.quoteAuthor
import kotlinx.android.synthetic.main.quotes_list_item.quoteContent

class QuotesAdapter(
    val quotes: Quotes
) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            quoteContent.text = quotes.quotes_phrase
            quoteAuthor.text = quotes.quotes_person
        }
    }

    override fun getLayout(): Int = R.layout.quotes_list_item
}