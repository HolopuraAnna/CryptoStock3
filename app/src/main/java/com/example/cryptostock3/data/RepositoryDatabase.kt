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

    //val retrofitObject = RetrofitObject()

    override suspend fun loadData() {
//        withContext(Dispatchers.IO) {
//            retrofitObject.getAndroid { response ->
//                response?.let {
//                    var list1 = mutableListOf<StockEntity>()
//                    it.data?.forEach { i ->
//                        Log.d("XXX", "fetchAndSaveData item: $i")
//                        val imageUrl = "https://www.cryptocompare.com${i.coinInfo?.imageUrl ?: ""}"
//
//                        val entity = StockEntity(
//                            0,
//                            i.coinInfo?.fullName ?: "Unknown",
//                            i.rAW?.uSD?.pRICE.toString(),
//                            i.rAW?.uSD?.lOWDAY.toString(),
//                            i.rAW?.uSD?.hIGHDAY.toString(),
//                            i.rAW?.uSD?.lASTMARKET.toString(),
//                            imageUrl
//                        )
//                        list1.add(entity)
//
//                    }
//                    CoroutineScope(Dispatchers.Default).launch {
//                        dao.insert(list1)
//                    }
//
//
//                }
//            }
//        }
    }
}