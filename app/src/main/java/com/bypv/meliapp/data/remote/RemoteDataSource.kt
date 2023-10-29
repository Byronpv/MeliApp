package com.bypv.meliapp.data.remote

import com.bypv.meliapp.core.RetrofitClient
import com.bypv.meliapp.data.model.ProductItemDescriptionModel
import com.bypv.meliapp.data.model.ProductResponse

/**
 * This class is responsible for handling remote data sources for products.
 */
class RemoteDataSource {

    private val service = RetrofitClient.getRetrofit().create(ProductService::class.java)

    suspend fun getProducts(categoryId: String): ProductResponse {
        return service.getProducts(categoryId)
    }

    suspend fun getDescriptionProduct(productId: String): ProductItemDescriptionModel {
        return service.getDescription(productId)
    }

    suspend fun getPicturesProduct(productId: String): ProductItemDescriptionModel {
        return service.getPictures(productId)
    }
}