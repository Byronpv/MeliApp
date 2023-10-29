package com.bypv.meliapp.presentation.viewModels.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bypv.meliapp.domain.ProductRepository
import com.bypv.meliapp.data.remote.RemoteDataSource
import com.bypv.meliapp.presentation.viewModels.ProductListDetailViewModel

/**
 * Factory class for creating instances of [ProductListDetailViewModel].
 * Implements [ViewModelProvider.Factory] interface.
 * 
 * @constructor Creates an instance of [ProductDetailViewModelFactory].
 */
class ProductDetailViewModelFactory : ViewModelProvider.Factory {

    /**
     * Creates a new instance of [ProductListDetailViewModel] using the [ProductRepository] class.
     * 
     * @param modelClass The class of the view model to be created.
     * @return The created view model instance.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ProductRepository(
            remoteDataSource = RemoteDataSource(),
        )
        return ProductListDetailViewModel(repository) as T
    }
}