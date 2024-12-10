package com.example.cryptostock3.data.db

import com.example.cryptostock3.data.api.ApiResponse
import com.example.cryptostock3.data.api.UsdData
import com.example.cryptostock3.domain.StockItem

fun StockItem.toStockEntity() : StockEntity {
    return StockEntity(
        fromSymbol = this.fromSymbol,
        toSymbol = this.toSymbol,
        lastMarket = this.lastMarket,
        price = this.price,
        lastUpdate = this.lastUpdate,
        supply = this.highDay,
        marketCap = this.lowDay,
        imageUrl = this.imageUrl
    )
}

fun entitiesToItems(entities: List<StockEntity>) = entities.map { it.toStockItem() }