package com.bypv.meliapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductResponse(
    @SerializedName("results") val results: MutableList<ProductModel> = mutableListOf()
): Serializable