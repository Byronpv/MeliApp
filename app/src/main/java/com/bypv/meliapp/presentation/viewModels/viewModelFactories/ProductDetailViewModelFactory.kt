package com.bypv.meliapp.presentation.viewModels.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bypv.meliapp.data.ProductRepository
import com.bypv.meliapp.data.remote.RemoteDataSource
import com.bypv.meliapp.presentation.viewModels.ProductListDetailViewModel

class ProductDetailViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ProductRepository(
            remoteDataSource = RemoteDataSource(),
        )
        return ProductListDetailViewModel(repository) as T
    }
}