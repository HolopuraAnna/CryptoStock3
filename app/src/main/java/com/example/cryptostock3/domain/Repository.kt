package com.example.cryptostock3.domain

import androidx.lifecycle.LiveData

interface Repository {
    val itemsLiveData: LiveData<List<StockItem>>
    suspend fun getItem(fromSymbol: String?): StockItem
    suspend fun loadData()
}