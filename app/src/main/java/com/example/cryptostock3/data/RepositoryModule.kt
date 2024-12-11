package com.example.cryptostock3.data

import com.example.cryptostock3.domain.Repository
import com.example.cryptostock3.data.RepositoryDatabase
import com.example.cryptostock3.data.api.StockService
import com.example.cryptostock3.domain.usecases.LoadItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(): Repository {
        return RepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideLoadItemUseCase(repository: Repository): LoadItemUseCase {
        return LoadItemUseCase(repository)
    }
}