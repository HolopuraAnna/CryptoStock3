package com.example.cryptostock3.data.api

import com.example.cryptostock3.domain.StockItem
import com.google.gson.annotations.SerializedName

data class StockData(
    @SerializedName("CoinInfo") val coinInfo: CoinInfo?,
    @SerializedName("RAW") val raw: RawData?,
    @SerializedName("DISPLAY") val display: DisplayData?
) {
    fun toStockItem(): StockItem {
        return StockItem(
            fromSymbol = this.raw?.usd?.fromSymbol?: StockItem.UNDEFINED_FSYM,
            toSymbol = this.raw?.usd?.toSymbol,
            lastMarket = this.display?.usdDisplay?.lastMarket,
            price = this.raw?.usd?.price.toString(),
            lastUpdate = this.raw?.usd?.lastUpdate,
            highDay = this.raw?.usd?.highDay.toString(),
            lowDay = this.raw?.usd?.lowDay.toString(),
            imageUrl = "https://www.cryptocompare.com${this.coinInfo?.imageUrl ?: ""}"
        )
    }
}