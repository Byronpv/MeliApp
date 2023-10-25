package com.bypv.meliapp.presentation.viewModels.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bypv.meliapp.data.ProductRepository
import com.bypv.meliapp.data.remote.RemoteDataSource
import com.bypv.meliapp.presentation.viewModels.ProductListViewModel

class ProductViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ProductRepository(
            remoteDataSource = RemoteDataSource(),
        )
        return ProductListViewModel(repository) as T
    }
}