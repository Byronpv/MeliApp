package com.bypv.meliapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductItemDescriptionModel(
    @SerializedName("plain_text") val plain_text: String,
    @SerializedName("body") val body: PicturesProductModel

): Serializable