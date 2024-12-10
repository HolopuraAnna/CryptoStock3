package com.example.cryptostock3.domain

data class CurrencyItem (
    val fromSymbol : String?,
    val toSymbol: String?,
    val lastMarket: String?,
    val price: String?,
    val lastUpdate: String?,
    val highDay: String?,
    val lowDay: String?,
    val imageUrl: String?
)