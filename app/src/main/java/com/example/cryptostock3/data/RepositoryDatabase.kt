package com.example.cryptostock3.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.cryptostock3.data.db.StockDatabase
import com.example.cryptostock3.data.db.StockEntity
import com.example.cryptostock3.data.db.entitiesToItems
import com.example.cryptostock3.domain.Repository
import com.example.cryptostock3.domain.StockItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.cryptostock3.data.api.RetrofitObject
import javax.inject.Inject

class RepositoryDatabase @Inject constructor(@ApplicationContext context: Context) : Repository {

    private val dao = StockDatabase.getDatabase(context).wordDao()

    override val itemsLiveData: LiveData<List<StockItem>>
        get() {
            val entityLiveData: LiveData<List<StockEntity>> = dao.itemsLiveData()

            val mediatorLiveData = MediatorLiveData<List<StockItem>>()

            mediatorLiveData.addSource(entityLiveData) { entities ->
                mediatorLiveData.value = entitiesToItems(entities)
            }

            return mediatorLiveData
        }


    override suspend fun getItem(fromSymbol: String?): StockItem {
        return dao.getItem(fromSymbol).toStockItem()
    }


    override suspend fun loadData() {
        try {
            val response = RetrofitObject.stockService.getAndroid()

            if (response.isSuccessful) {
                val stockItems = response.body()?.data?.map { it.toStockItem() } ?: emptyList()

                val stockEntities = stockItems.map { stockItem ->
                    StockEntity(
                        fromSymbol = stockItem.fromSymbol,
                        toSymbol = stockItem.toSymbol,
                        lastMarket = stockItem.lastMarket,
                        price = stockItem.price,
                        lastUpdate = stockItem.lastUpdate,
                        supply = stockItem.highDay,
                        marketCap = stockItem.lowDay,
                        imageUrl = stockItem.imageUrl
                    )
                }

                withContext(Dispatchers.IO) {
                    dao.insert(stockEntities)
                }
            } else {
                Log.e("XXXX", "Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("XXXX", "Failed to load data: ${e.localizedMessage}")
        }
    }
}