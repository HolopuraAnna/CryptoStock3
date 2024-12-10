package com.example.cryptostock3.domain.usecases

import com.example.cryptostock3.domain.Repository
import com.example.cryptostock3.domain.StockItem
import javax.inject.Inject

class GetItemUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(fromSymbol: String?): StockItem {
        return repository.getItem(fromSymbol)
    }
}