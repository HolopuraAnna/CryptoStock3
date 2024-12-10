package com.example.cryptostock3.domain

import androidx.lifecycle.LiveData

interface Repository {
    val itemsLiveData: LiveData<List<StockItem>>
    suspend fun getItem(id: Int): StockItem
}