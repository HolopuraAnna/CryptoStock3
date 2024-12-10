package com.example.cryptostock3.domain.usecases

import com.example.cryptostock3.domain.Repository
import javax.inject.Inject

class LoadItemUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke()  {
        return repository.loadData()
    }
}