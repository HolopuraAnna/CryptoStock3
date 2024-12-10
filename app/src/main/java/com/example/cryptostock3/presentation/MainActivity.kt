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

        loadData()
    }


    private fun loadData() {
        lifecycleScope.launch {
            try {
                //Toast.makeText(this@MainActivity, "Fetching the crypto magic!", Toast.LENGTH_SHORT).show()

                val response = withContext(Dispatchers.IO) {
                    RetrofitObject.stockService.getAndroid()
                }

                if (response.isSuccessful) {
                    val stocks = response.body()?.data?.map { it.toStockItem() } ?: emptyList()
                    Log.d("XXXX", "Loaded ${stocks.size} stocks $stocks")
                    stockItemsAdapter.submitList(stocks)
                } else {
                    Log.e("XXXX", "Error: ${response.code()} - ${response.message()}")
                    Toast.makeText(this@MainActivity, "Failed to fetch stocks: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Log.e("XXXX", "Something went wrong: ${e.localizedMessage}")
                Toast.makeText(this@MainActivity, "Oops! Something went wrong. Please try again.", Toast.LENGTH_LONG).show()
            }
        }
    }




}
