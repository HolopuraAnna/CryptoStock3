package com.example.cryptostock3.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptostock3.R
import com.example.cryptostock3.data.api.RetrofitObject
import com.example.cryptostock3.domain.StockItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val stockItemsAdapter = StockItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.stockItems)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = stockItemsAdapter

        fetchAndDisplayStocks()
    }

    private fun fetchAndDisplayStocks() {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitObject.stockService.getStocks()
                }
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val stockItems = apiResponse?.data?.mapNotNull { stockData ->
                        val rawUsd = stockData?.raw?.usd
                        rawUsd?.let {
                            StockItem(
                                fromSymbol = it.fromSymbol,
                                toSymbol = it.toSymbol,
                                lastMarket = null,
                                price = it.price.toString(),
                                lastUpdate = it.lastUpdate,
                                highDay = it.highDay.toString(),
                                lowDay = it.lowDay.toString(),
                                imageUrl = "https://www.cryptocompare.com${stockData.coinInfo?.imageUrl}"
                            )
                        }
                    }
                    stockItems?.let {
                        stockItemsAdapter.submitList(it)
                    } ?: run {
                        showToast("No data available.")
                    }
                } else {
                    showToast("Error fetching data: ${response.message()}")
                    Log.i("XXX", response.toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showToast("An error occurred: ${e.message}")

            }
        }
    }




    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

