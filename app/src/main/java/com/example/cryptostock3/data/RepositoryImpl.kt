package com.example.cryptostock3.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptostock3.domain.StockItem
import com.example.cryptostock3.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

//    private val items: MutableList<StockItem> = mutableListOf()

    private val items = setOf<StockItem>()

    private val _itemsLiveData = MutableLiveData<List<StockItem>>()

    override val itemsLiveData: LiveData<List<StockItem>>
        get() = _itemsLiveData


    override suspend fun getItem(fromSymbol: String?): StockItem {
        return items.find {
            it.fromSymbol == fromSymbol
        } ?: throw IllegalStateException("Item $fromSymbol isn't found")
    }

    override suspend fun loadData() {
        TODO("Not yet implemented")
    }

    init {
        update()
    }

    private fun update() {
        _itemsLiveData.value = items.toList()
    }
}