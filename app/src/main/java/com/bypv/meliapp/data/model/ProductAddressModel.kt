package com.bypv.meliapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductAddressModel(
    @SerializedName("state_name") val stateName: String,
    @SerializedName("city_name") val cityName: String
):Serializable
