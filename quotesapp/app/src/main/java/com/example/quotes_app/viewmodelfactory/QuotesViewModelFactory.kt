package com.example.quotes_app.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quotes_app.data.repository.QuoteRepository
import com.example.quotes_app.viewmodel.QuotesViewModel

class QuotesViewModelFactory(
    private val quotesRepository: QuoteRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(quotesRepository) as T
    }
}