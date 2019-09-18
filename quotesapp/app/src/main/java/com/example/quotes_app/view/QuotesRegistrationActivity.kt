package com.example.quotes_app.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quotes_app.R
import com.example.quotes_app.databinding.ActivityQuotesRegistrationBindingImpl
import com.example.quotes_app.view.base.ScopedActivity
import com.example.quotes_app.viewmodel.QuotesRegistrationViewModel
import com.example.quotes_app.viewmodelfactory.QuotesRegistrationViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class QuotesRegistrationActivity : ScopedActivity(), KodeinAware {

    override val kodein by closestKodein()

    private lateinit var binding: ActivityQuotesRegistrationBindingImpl
    private lateinit var viewModel: QuotesRegistrationViewModel
    private val viewModelFactory: QuotesRegistrationViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes_registration)
        supportActionBar?.hide()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_quotes_registration)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(QuotesRegistrationViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.clickedBack.observe(this, Observer { onBackPressed() })
    }
}
