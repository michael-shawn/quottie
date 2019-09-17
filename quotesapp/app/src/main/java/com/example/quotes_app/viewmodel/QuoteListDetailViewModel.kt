package com.example.quotes_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quotes_app.data.repository.QuoteRepository
import com.example.quotes_app.internal.lazyDeferred

class QuoteListDetailViewModel(
    private val quotesRepository: QuoteRepository
) : ViewModel() {

    var id = MutableLiveData<Int>()

    init {
        id.value = 0
    }

    val quoteDetailEntries by lazyDeferred {
        quotesRepository.readDetail(id.value!!)
    }
}
