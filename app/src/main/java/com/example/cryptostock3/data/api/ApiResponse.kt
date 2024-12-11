package com.example.cryptostock3.data.api

import androidx.room.Entity
import com.example.cryptostock3.domain.StockItem
import com.google.gson.annotations.SerializedName

data class ApiResponse(
//    @SerializedName("Message") val message: String?,
//    @SerializedName("Type") val type: Int?,
    @SerializedName("Data") val data: List<StockData>?
){
    fun toListStockItem(): List<StockItem>? = this.data?.map { it.toStockItem() }
}