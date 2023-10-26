package com.bypv.meliapp.presentation.viewModels.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bypv.meliapp.core.AndroidLogger
import com.bypv.meliapp.domain.ProductRepository
import com.bypv.meliapp.data.remote.RemoteDataSource
import com.bypv.meliapp.presentation.viewModels.ProductListDetailViewModel
import kotlin.math.log

class ProductDetailViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ProductRepository(
            remoteDataSource = RemoteDataSource(),
        )
        val logger = AndroidLogger()
        return ProductListDetailViewModel(repository, logger) as T
    }
}