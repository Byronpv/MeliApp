package com.bypv.meliapp.data.model

import com.google.gson.annotations.SerializedName

data class PicturesProductModel(
    @SerializedName("secure_thumbnail") val secureUrl: String?,
)
