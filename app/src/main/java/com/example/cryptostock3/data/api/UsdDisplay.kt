package com.example.cryptostock3.data.api

import com.google.gson.annotations.SerializedName

data class UsdDisplay(
    @SerializedName("LASTMARKET") val lastMarket: String?
)