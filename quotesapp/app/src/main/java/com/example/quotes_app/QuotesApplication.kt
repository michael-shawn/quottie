package com.example.quotes_app

import android.app.Application
import com.example.quotes_app.data.database.QuotesDatabase
import com.example.quotes_app.data.repository.QuoteRepository
import com.example.quotes_app.data.repository.QuoteRepositoryImpl
import com.example.quotes_app.viewmodel.QuoteListDetailViewModelFactory
import com.example.quotes_app.viewmodel.QuotesRegistrationViewModelFactory
import com.example.quotes_app.viewmodel.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class QuotesApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@QuotesApplication))

        bind() from singleton { QuotesDatabase(instance()) }
        bind() from provider { QuotesRegistrationViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }
        bind() from provider { QuoteListDetailViewModelFactory(instance()) }
        bind<QuoteRepository>() with singleton { QuoteRepositoryImpl(instance()) }

    }
}