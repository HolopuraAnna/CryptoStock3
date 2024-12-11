package com.example.cryptostock3.data.api

import com.google.gson.annotations.SerializedName

data class DisplayData(
    @SerializedName("USD") val usdDisplay: UsdDisplay?
)