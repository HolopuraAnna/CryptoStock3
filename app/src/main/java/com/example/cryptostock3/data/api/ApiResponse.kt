package com.example.cryptostock3.data.api

import com.example.cryptostock3.data.db.StockEntity
import com.example.cryptostock3.domain.StockItem
import com.google.gson.annotations.SerializedName
data class ApiResponse(
//    @SerializedName("Message") val message: String?,
//    @SerializedName("Type") val type: Int?,
    @SerializedName("Data") val data: List<StockData>?
){
    fun toListStockItem(): List<StockItem>? = this.data?.map { it.toStockItem() }
}
data class StockData(
    @SerializedName("CoinInfo") val coinInfo: CoinInfo?,
    @SerializedName("RAW") val raw: RawData?,
    @SerializedName("DISPLAY") val display: DisplayData?
) {
    fun toStockItem(): StockItem {
        return StockItem(
            fromSymbol = this.raw?.usd?.fromSymbol,
            toSymbol = this.raw?.usd?.toSymbol,
            lastMarket = this.display?.usdDisplay?.lastMarket,
            price = this.raw?.usd?.price.toString(),
            lastUpdate = this.raw?.usd?.lastUpdate,
            highDay = this.raw?.usd?.highDay.toString(),
            lowDay = this.raw?.usd?.lowDay.toString(),
            imageUrl = this.coinInfo?.imageUrl
        )
    }
}
data class CoinInfo(
    @SerializedName("Name") val name: String?,
    @SerializedName("FullName") val fullName: String?,
    @SerializedName("ImageUrl") val imageUrl: String?
)
data class RawData(
    @SerializedName("USD") val usd: UsdData?
)

data class DisplayData(
    @SerializedName("USD") val usdDisplay: UsdDisplay?
)

data class UsdData(
    @SerializedName("FROMSYMBOL") val fromSymbol: String?,
    @SerializedName("TOSYMBOL") val toSymbol: String?,
    @SerializedName("PRICE") val price: Double?,
    @SerializedName("LASTUPDATE") val lastUpdate: Long?,
    @SerializedName("HIGHDAY") val highDay: Double?,
    @SerializedName("LOWDAY") val lowDay: Double?
)

data class UsdDisplay(
    @SerializedName("LASTMARKET") val lastMarket: String?
)