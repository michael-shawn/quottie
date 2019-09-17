package com.example.quotes_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quotes_app.data.repository.QuoteRepository
import com.example.quotes_app.internal.lazyDeferred

class QuotesViewModel(
    private val quotesRepository: QuoteRepository
) : ViewModel() {

    var clicked = MutableLiveData<Boolean>()
    var id = MutableLiveData<Int>()

    init {
        id.value = 0
    }

    val quotesEntries by lazyDeferred {
        quotesRepository.read()
    }

    fun clickedRegistration() {
        clicked.value = true
    }

    fun onDeleteQuote(id: Int) {
        quotesRepository.delete(id)
    }
}