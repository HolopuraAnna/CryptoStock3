package com.example.cryptostock3.domain.usecases

import androidx.lifecycle.LiveData
import com.example.cryptostock3.domain.Repository
import com.example.cryptostock3.domain.StockItem
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke() : LiveData<List<StockItem>> {
        return repository.itemsLiveData
    }
}