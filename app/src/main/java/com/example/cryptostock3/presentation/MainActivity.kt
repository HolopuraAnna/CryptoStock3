package com.example.cryptostock3.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptostock3.R
import com.example.cryptostock3.data.api.RetrofitObject
import com.example.cryptostock3.databinding.ActivityMainBinding
import com.example.cryptostock3.domain.StockItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        viewModel.itemsLiveData.observe(this) {
            Log.d("XXXX", "getItemUseCase: $it")

            stockItemsAdapter.submitList(it)
        }

        binding.stockItems.layoutManager = LinearLayoutManager(this)
        binding.stockItems.adapter = stockItemsAdapter

        stockItemsAdapter.itemsInteractionListener = object : StockItemsAdapter.ItemsInteractionListener {
            override fun onClick(stockItem: StockItem) {
                Log.d("XXXX", "onClick: $stockItem")

                startStockItemActivity(stockItem)
            }
        }
    }


    private fun startStockItemActivity(stockItem: StockItem) {
        val intent = Intent(this, StockItemActivity::class.java)
            .apply {
            putExtra(EXTRA_MODE, MODE_EDIT)
            putExtra(EXTRA_ITEM_FSYM, stockItem.fromSymbol)
        }
        startActivity(intent)
    }
}
