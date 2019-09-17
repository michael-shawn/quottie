package com.example.quotes_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quotes_app.data.repository.QuoteRepository

class QuotesRegistrationViewModelFactory(
    private val quotesRepository: QuoteRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesRegistrationViewModel(quotesRepository) as T
    }
}