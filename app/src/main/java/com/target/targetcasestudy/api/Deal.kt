package com.target.targetcasestudy.api

import com.google.gson.annotations.SerializedName

data class Deal(
    val id: String,

    val title: String,

    val aisle: String,

    val description: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("regular_price")
    val salePrice: Price,

    val fulfillment: String,

    val availability: String


)
