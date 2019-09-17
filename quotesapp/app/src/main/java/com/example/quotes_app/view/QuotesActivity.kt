package com.example.quotes_app.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotes_app.R
import com.example.quotes_app.adapter.QuotesAdapter
import com.example.quotes_app.constant.CONSTANT
import com.example.quotes_app.data.database.model.Quotes
import com.example.quotes_app.databinding.ActivityQuotesBinding
import com.example.quotes_app.view.base.ScopedActivity
import com.example.quotes_app.viewmodel.QuotesViewModel
import com.example.quotes_app.viewmodel.QuotesViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_list_item.view.btnDelete
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class QuotesActivity : ScopedActivity(), KodeinAware {

    override val kodein by closestKodein()

    private lateinit var binding: ActivityQuotesBinding
    private lateinit var viewModel: QuotesViewModel
    private val viewModelFactory: QuotesViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_quotes)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(QuotesViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.clicked.observe(this, Observer { gotoRegistration() })

        bindUI()
    }

    private fun gotoRegistration() {
        startActivity(Intent(this, QuotesRegistrationActivity::class.java))
        overridePendingTransition(
            R.transition.slide_in_right,
            R.transition.slide_out_left
        )
    }

    private fun bindUI() = launch(Dispatchers.Main) {

        val quotesListEntries = viewModel.quotesEntries.await()
        quotesListEntries.observe(this@QuotesActivity, Observer {
            if (it.isNullOrEmpty()) {
                binding.quotesStatus.visibility = VISIBLE
                binding.recyclerView.visibility = INVISIBLE
            } else {
                initRecyclerView(it.toQuotesItems())
                binding.quotesStatus.visibility = GONE
            }
        })
    }

    private fun List<Quotes>.toQuotesItems(): List<QuotesAdapter> {
        return this.map {
            QuotesAdapter(it)
        }
    }

    @SuppressLint("RestrictedApi")
    private fun initRecyclerView(items: List<QuotesAdapter>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        binding.recyclerView.visibility = VISIBLE
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@QuotesActivity.applicationContext)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemLongClickListener { item, view ->
            (item as QuotesAdapter)?.let {
                view.btnDelete.visibility = VISIBLE
            }
            return@setOnItemLongClickListener true
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as QuotesAdapter)?.let { entry ->

                if (view.btnDelete.isPressed) {
                    view.btnDelete.setOnClickListener {
                        viewModel.id.value = entry.quotes.quotes_id
                        viewModel.id.value?.toInt()?.let { value -> viewModel.onDeleteQuote(value) }
                    }
                }

                if (view.btnDelete.visibility == VISIBLE) {
                    view.btnDelete.visibility = INVISIBLE
                    return@let
                }

                val id = entry.quotes.quotes_id
                val intent = Intent(this, QuoteListDetailActivity::class.java)
                intent.putExtra(CONSTANT.QUOTE_ID, id)
                startActivity(intent)
                overridePendingTransition(
                    R.transition.slide_in_right,
                    R.transition.slide_out_left
                )
            }
        }
    }
}
