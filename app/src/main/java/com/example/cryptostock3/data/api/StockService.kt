package com.example.cryptostock3.data.api

import com.example.cryptostock3.domain.StockItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StockService {

    @GET("/top/totalvolfull") // Replace with the base endpoint
    suspend fun getStocks(
        @Query("limit") limit: Int = 30,
        @Query("tsym") currency: String = "USD"
    ): Response<List<StockItem>>
}
