package com.bypv.meliapp.data

import com.bypv.meliapp.data.remote.RemoteDataSource

class ProductRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getProducts(categoryId: String) = remoteDataSource.getProducts(categoryId)

    suspend fun getDescriptionProduct(productId: String) = remoteDataSource.getDescriptionProduct(productId)

    suspend fun getPicturesProduct(productId: String) = remoteDataSource.getPicturesProduct(productId)
}