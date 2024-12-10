package com.example.cryptostock3.domain.usecases

import com.example.cryptostock3.domain.Repository
import com.example.cryptostock3.domain.StockItem

class GetStockUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(itemId: Int): StockItem {
        return repository.getItem(itemId)
    }
}