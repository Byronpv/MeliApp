package com.bypv.meliapp.data.remote

import com.bypv.meliapp.BuildConfig
import com.bypv.meliapp.data.model.ProductItemDescriptionModel
import com.bypv.meliapp.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("/sites/MLA/search?")
    suspend fun getProducts(
        @Query("q") item: String
    ): ProductResponse

    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("items/{idItem}/description")
    suspend fun getDescription(@Path("idItem") productId: String): ProductItemDescriptionModel

    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("items?")
    suspend fun getPictures(
        @Query("ids") ids: String
    ): ProductItemDescriptionModel
}