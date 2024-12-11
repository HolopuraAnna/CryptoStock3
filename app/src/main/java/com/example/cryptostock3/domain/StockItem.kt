package com.example.cryptostock3.domain

data class StockItem (
    val fromSymbol: String = UNDEFINED_FSYM,
    val toSymbol: String?,
    val lastMarket: String?,
    val price: String?,
    val lastUpdate: Long?, // Change from String to Long
    val highDay: String?,
    val lowDay: String?,
    val imageUrl: String?)
{
    companion object {
        const val UNDEFINED_FSYM = "A"
    }

}
