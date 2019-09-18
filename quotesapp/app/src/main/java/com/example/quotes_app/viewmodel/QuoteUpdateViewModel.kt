package com.example.quotes_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quotes_app.data.repository.QuoteRepository

class QuoteUpdateViewModel(
    private val quotesRepository: QuoteRepository
) : ViewModel() {

    var clickedBack = MutableLiveData<Boolean>()
    var afterUpdate = MutableLiveData<Boolean>()

    var id = MutableLiveData<Int>()
    var quotablePhrase = MutableLiveData<String>()
    var quotedBy = MutableLiveData<String>()

    init {
        id.value = 0
        quotablePhrase.value = ""
        quotedBy.value = ""
    }

    fun onBackClicked() {
        clickedBack.value = true
    }

    fun onUpdateQuote() {
        afterUpdate.value = true
        quotesRepository.updateQuote(
            id.value!!,
            quotablePhrase.value!!,
            quotedBy.value!!
        )

        quotablePhrase.value = ""
        quotedBy.value = ""
    }
}