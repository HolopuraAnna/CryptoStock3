package com.example.cryptostock3.domain

data class CurrencyItem (
    val fromSymbol: String?,
    val toSymbol: String?,
    val lastMarket: String?,
    val price: String?,
    val lastUpdate: Long?, // Change from String to Long
    val highDay: String?,
    val lowDay: String?,
    val imageUrl: String?
)
