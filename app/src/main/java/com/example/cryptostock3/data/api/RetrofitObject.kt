package com.example.cryptostock3.data.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private const val BASE_URL = "https://min-api.cryptocompare.com/data/" // Replace with your actual API base URL

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val stockService: StockService by lazy {
        retrofit.create(StockService::class.java)
    }
}
