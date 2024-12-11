package com.example.cryptostock3.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptostock3.databinding.ActivityItemBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.bumptech.glide.Glide


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

    private fun parseItemIntent(intent: Intent) {
        val fromSymbol = intent.getStringExtra(EXTRA_ITEM_FSYM).toString()
        if (fromSymbol != null) {
            viewModel.getItem(fromSymbol)
        } else {
            Log.e("StockItemViewActivity", "No fromSymbol found in intent")
        }
    }


    @SuppressLint("SetTextI18n")
    private fun setupLiveData() {
        viewModel.itemLiveData.observe(this) {
            with(binding) {
                currency.setText("${it.fromSymbol} / ${it.toSymbol}")
                price.setText(it.price)
                min.setText(it.lowDay)
                max.setText(it.highDay)
                lastDeal.setText(it.lastMarket)
                update.setText(it.lastUpdate.toString())
                Glide.with(this@StockItemViewActivity)
                    .load(it.imageUrl)
                    .into(pic)
            }
        }
    }
}
