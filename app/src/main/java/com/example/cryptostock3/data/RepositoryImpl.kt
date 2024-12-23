package com.example.cryptostock3.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptostock3.data.api.RetrofitObject
import com.example.cryptostock3.domain.StockItem
import com.example.cryptostock3.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

//    private val items: MutableList<StockItem> = mutableListOf()

    private val items = mutableSetOf<StockItem>()

    private val _itemsLiveData = MutableLiveData<List<StockItem>>()


    override val itemsLiveData: LiveData<List<StockItem>>
        get() = _itemsLiveData


    override suspend fun getItem(fromSymbol: String?): StockItem {
        Log.d("RepositoryImpl", "Looking for item with fromSymbol: $fromSymbol")
        items.forEach { Log.d("RepositoryImpl", "Available item: ${it.fromSymbol}") }
        return items.find {
            it.fromSymbol == fromSymbol
        } ?: throw IllegalStateException("Item $fromSymbol isn't found")
    }



    override suspend fun loadData() {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitObject.stockService.getAndroid()
            }

            if (response.isSuccessful) {
                val stockItems = response.body()?.data?.map { it.toStockItem() } ?: emptyList()
                Log.d("XXXX", "Loaded ${stockItems.size} stocks $stockItems")
                items.clear()
                items.addAll(stockItems)
                withContext(Dispatchers.Main) {
                    _itemsLiveData.value = stockItems
                }
            } else {
                Log.e("XXXX", "Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("XXXX", "Failed to load data: ${e.localizedMessage}")
        }
    }



    init {
        update()
    }


    private fun update() {
        _itemsLiveData.value = items.toList()
    }
}