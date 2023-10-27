package com.bypv.meliapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("permalink") val url: String,
    @SerializedName("condition") val conditionModel:String,
    @SerializedName("currency_id") val currency: String?,
    @SerializedName("official_store_name") val storeName: String?,
) : Serializable