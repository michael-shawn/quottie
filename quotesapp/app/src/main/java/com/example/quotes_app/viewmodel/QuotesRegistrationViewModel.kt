package com.example.quotes_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quotes_app.data.database.model.Quotes
import com.example.quotes_app.data.repository.QuoteRepository

class QuotesRegistrationViewModel(
    private val quotesRepository: QuoteRepository
) : ViewModel() {

    var clicked = MutableLiveData<Boolean>()

    var quotePhrase = MutableLiveData<String>()
    var quotePerson = MutableLiveData<String>()

    init {
        clicked.value = false
        quotePhrase.value = ""
        quotePerson.value = ""
    }

    fun onAddQuoteClicked() {
        clicked.value = true
        quotesRepository.insert(Quotes(quotePhrase.value, quotePerson.value))

        quotePhrase.value = ""
        quotePerson.value = ""
    }
}