package com.example.cryptostock3.data.db

import androidx.room.Entity
import com.example.cryptostock3.domain.StockItem
import com.google.gson.annotations.SerializedName


@Entity(tableName = "stock_item_list")
data class StockEntity(

    @SerializedName("FROMSYMBOL")
    val fromSymbol: String?,

    @SerializedName("TOSYMBOL")
    val toSymbol: String?,

    @SerializedName("LASTMARKET")
    val lastMarket: String?,

    @SerializedName("PRICE")
    val price: String?,

    @SerializedName("LASTUPDATE")
    val lastUpdate: String?,

    @SerializedName("HIGHDAY")
    val supply: String?,

    @SerializedName("LOWDAY")
    val marketCap: String?,

    @SerializedName("IMAGEURL")
    val imageUrl: String?
) {
    fun toCurrencyItem(): StockItem {
        return StockItem(
            fromSymbol = this.fromSymbol,
            toSymbol = this.toSymbol,
            lastMarket = this.lastMarket,
            price = this.price,
            lastUpdate = this.lastUpdate,
            highDay = this.supply,
            lowDay = this.marketCap,
            imageUrl = this.imageUrl
        )
    }
}