package com.example.quotes_app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.quotes_app.R
import com.example.quotes_app.databinding.ActivityQuotesRegistrationBindingImpl
import com.example.quotes_app.viewmodel.QuotesRegistrationViewModel
import com.example.quotes_app.viewmodel.QuotesRegistrationViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class QuotesRegistrationActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    private lateinit var binding: ActivityQuotesRegistrationBindingImpl
    private lateinit var viewModel: QuotesRegistrationViewModel
    private val viewModelFactory: QuotesRegistrationViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes_registration)
        //supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_quotes_registration)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(QuotesRegistrationViewModel::class.java)

        binding.viewModel = viewModel
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.transition.slide_in_left,
            R.transition.slide_out_right
        )
    }
}
