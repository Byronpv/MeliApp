package com.bypv.meliapp.data.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("results") val results: MutableList<ProductModel> = mutableListOf()
)