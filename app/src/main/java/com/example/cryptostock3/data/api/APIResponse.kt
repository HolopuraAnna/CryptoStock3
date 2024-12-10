package com.example.cryptostock3.data.api

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("Message") val message: String?,
    @SerializedName("Type") val type: Int?,
    @SerializedName("Data") val data: StockData?
)



data class StockData(
    @SerializedName("CoinInfo") val coinInfo: CoinInfo?,
    @SerializedName("RAW") val raw: RawData?
)

data class CoinInfo(
    @SerializedName("Name") val name: String?,
    @SerializedName("FullName") val fullName: String?,
    @SerializedName("ImageUrl") val imageUrl: String?
)

data class RawData(
    @SerializedName("USD") val usd: UsdData?
)

data class UsdData(
    @SerializedName("FROMSYMBOL") val fromSymbol: String?,
    @SerializedName("TOSYMBOL") val toSymbol: String?,
    @SerializedName("PRICE") val price: Double?,
    @SerializedName("LASTUPDATE") val lastUpdate: Long?,
    @SerializedName("HIGHDAY") val highDay: Double?,
    @SerializedName("LOWDAY") val lowDay: Double?
)
