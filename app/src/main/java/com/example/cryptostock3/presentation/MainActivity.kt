package com.example.cryptostock3.presentation

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptostock3.R
import com.example.cryptostock3.databinding.ActivityMainBinding
import com.example.cryptostock3.domain.StockItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<StockViewModel>()
    private val stockItemsAdapter = StockItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.loadData()

        viewModel.itemsLiveData.observe(this) { items ->
            Log.d("XXXX", "getItemUseCase: $items")
            stockItemsAdapter.submitList(items)
        }

        binding.stockItems.layoutManager = LinearLayoutManager(this)
        binding.stockItems.adapter = stockItemsAdapter

        stockItemsAdapter.itemsInteractionListener = object : StockItemsAdapter.ItemsInteractionListener {
            override fun onClick(stockItem: StockItem) {
                stockItem.fromSymbol?.let { symbol ->
                    if (isInSplitScreenMode()) {

                        showDetailsFragment(symbol)
                    } else {
                        startStockItemViewActivity(stockItem)

                    }
                } ?: Log.e("MainActivity", "fromSymbol is null for the clicked item")
            }
        }
    }

    private fun showDetailsFragment(fromSymbol: String) {
        val fragment = StockItemFragment.newInstance(fromSymbol)
        supportFragmentManager.beginTransaction()
            .replace(R.id.detailsContainer, fragment)
            .commit()
        findViewById<View>(R.id.detailsContainer)?.visibility = View.VISIBLE
    }

    private fun isInSplitScreenMode(): Boolean {
        val detailsContainer = findViewById<View?>(R.id.detailsContainer)
        return detailsContainer != null && resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
    private fun startStockItemViewActivity(stockItem: StockItem) {
        Log.d("MainActivity", "Starting StockItemViewActivity with fromSymbol: ${stockItem.fromSymbol}")
        val intent = Intent(this, StockItemViewActivity::class.java).apply {
            putExtra(EXTRA_ITEM_FSYM, stockItem.fromSymbol)
        }
        startActivity(intent)
    }



}
