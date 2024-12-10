package com.example.cryptostock3.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StockService {

    @GET("top/totalvolfull") // ?limit=30&tsym=USD
    fun getAndroid(
        @Query("limit") courseData1: String = "30",
        @Query("tsym") courseData2: String = "USD"
    ): Response<ApiResponse>
}
