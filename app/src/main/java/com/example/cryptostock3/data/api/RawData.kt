package com.example.cryptostock3.data.api

import com.google.gson.annotations.SerializedName

data class RawData(
    @SerializedName("USD") val usd: UsdData?
)