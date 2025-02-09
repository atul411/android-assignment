package com.target.targetcasestudy.api

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class DealResponse(
    @SerializedName("products")
    val deals: List<Deal>
)
