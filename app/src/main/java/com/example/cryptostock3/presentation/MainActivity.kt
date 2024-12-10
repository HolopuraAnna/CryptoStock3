package com.example.cryptostock3.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptostock3.R
import com.example.cryptostock3.domain.StockItem

class MainActivity : AppCompatActivity() {

    private val stockItemsAdapter = StockItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.stockItems)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = stockItemsAdapter

        // Create dummy data
        val dummyData = listOf(
            StockItem(
                fromSymbol = "BTC",
                toSymbol = "USD",
                lastMarket = "Binance",
                price = "34500.00",
                lastUpdate = 1700000000000L, // Timestamp in milliseconds
                highDay = "35000.00",
                lowDay = "34000.00",
                imageUrl = "https://example.com/btc.png"
            ),
            StockItem(
                fromSymbol = "ETH",
                toSymbol = "USD",
                lastMarket = "Coinbase",
                price = "2000.00",
                lastUpdate = 1733782924, // Timestamp in milliseconds
                highDay = "2100.00",
                lowDay = "1900.00",
                imageUrl = "https://example.com/eth.png"
            ),
            StockItem(
                fromSymbol = "XRP",
                toSymbol = "USD",
                lastMarket = "Kraken",
                price = "0.50",
                lastUpdate = 1700000000000L, // Timestamp in milliseconds
                highDay = "0.52",
                lowDay = "0.48",
                imageUrl = "https://example.com/xrp.png"
            )
        )

        // Set the dummy data to the adapter
        stockItemsAdapter.submitList(dummyData)
    }
}
