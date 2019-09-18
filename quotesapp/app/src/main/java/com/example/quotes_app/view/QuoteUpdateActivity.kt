package com.example.quotes_app.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quotes_app.R
import com.example.quotes_app.constant.CONSTANT
import com.example.quotes_app.databinding.ActivityQuoteUpdateBindingImpl
import com.example.quotes_app.view.base.ScopedActivity
import com.example.quotes_app.viewmodel.QuoteUpdateViewModel
import com.example.quotes_app.viewmodelfactory.QuoteUpdateViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class QuoteUpdateActivity : ScopedActivity(), KodeinAware {

    override val kodein by closestKodein()

    private lateinit var binding: ActivityQuoteUpdateBindingImpl
    private lateinit var viewModel: QuoteUpdateViewModel
    private val viewModelFactory: QuoteUpdateViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_update)
        supportActionBar?.hide()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_quote_update)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(QuoteUpdateViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.clickedBack.observe(this, Observer { onBackPressed() })

        bindUI()
    }

    private fun bindUI() {
        viewModel.id.value = intent.extras?.getInt(CONSTANT.QUOTE_ID)
        viewModel.quotablePhrase.value = intent.extras?.getString(CONSTANT.QUOTE_PHRASE)
        viewModel.quotedBy.value = intent.extras?.getString(CONSTANT.QUOTE_BY)
    }
}
