package com.example.cryptostock3.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptostock3.databinding.ActivityItemBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.example.cryptostock3.domain.StockItem



@AndroidEntryPoint
class StockItemViewActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityItemBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<StockItemViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        parseItemIntent(intent)
        setupLiveData()
    }

    private var mode: String = MODE_UNDEFINED
    private var fromSymbol = StockItem.UNDEFINED_FSYM

    private fun parseItemIntent(intent: Intent) {
        if (intent.hasExtra(EXTRA_MODE)) {
            mode = intent.getStringExtra(EXTRA_MODE) ?: MODE_UNDEFINED
            when (mode) {
                MODE_EDIT -> {
                    if (intent.hasExtra(EXTRA_ITEM_FSYM)) {
                        fromSymbol = intent.getStringExtra(EXTRA_ITEM_FSYM) ?: StockItem.UNDEFINED_FSYM
                        if (fromSymbol == StockItem.UNDEFINED_FSYM) {
                            throw IllegalArgumentException("Mode Edit, fromSymbol isn't defined")
                        }
                        viewModel.getItem(fromSymbol)
                    } else {
                        throw IllegalArgumentException("Mode Edit, fromSymbol isn't provided")
                    }
                }

                MODE_ADD -> {
                    // Initialize for adding new StockItem
                }

                MODE_UNDEFINED -> throw IllegalArgumentException("Mode isn't defined")
            }
        } else {
            throw IllegalArgumentException("Mode isn't defined")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupLiveData() {
        var observe = viewModel.itemLiveData.observe(this) { stockItem ->
            with(binding) {
                // Populate fields with data from the ViewModel
                currency.text = "${stockItem.fromSymbol} / ${stockItem.toSymbol}"
                price.text = stockItem.price ?: "N/A"
                min.text = stockItem.lowDay ?: "N/A"
                max.text = stockItem.highDay ?: "N/A"
                lastDeal.text = stockItem.lastMarket ?: "N/A"
                update.text = stockItem.lastUpdate?.toString() ?: "N/A"
            }
        }


    }
}
