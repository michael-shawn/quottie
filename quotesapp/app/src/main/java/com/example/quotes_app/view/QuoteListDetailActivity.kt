package com.example.quotes_app.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quotes_app.R
import com.example.quotes_app.constant.CONSTANT
import com.example.quotes_app.databinding.ActivityQuoteListDetailBinding
import com.example.quotes_app.view.base.ScopedActivity
import com.example.quotes_app.viewmodel.QuoteListDetailViewModel
import com.example.quotes_app.viewmodel.QuoteListDetailViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class QuoteListDetailActivity : ScopedActivity(), KodeinAware {
    override val kodein by closestKodein()

    private lateinit var binding: ActivityQuoteListDetailBinding
    private lateinit var viewModel: QuoteListDetailViewModel
    private val viewModelFactory: QuoteListDetailViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_list_detail)
        supportActionBar?.hide()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_quote_list_detail)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(QuoteListDetailViewModel::class.java)

        viewModel.id.value = intent.extras?.getInt(CONSTANT.QUOTE_ID)

        bindUI()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.transition.slide_in_left,
            R.transition.slide_out_right
        )
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val quoteDetailEntries = viewModel.quoteDetailEntries.await()
        quoteDetailEntries.observe(this@QuoteListDetailActivity, Observer {

            binding.txtQuotablePhrase.text = it.quotes_phrase
            binding.txtQuotedBy.text = String.format(
                getString(R.string.detail_person),
                it.quotes_person
            )
        })
    }
}
