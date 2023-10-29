package com.bypv.meliapp.presentation.viewModels.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bypv.meliapp.domain.ProductRepository
import com.bypv.meliapp.data.remote.RemoteDataSource
import com.bypv.meliapp.presentation.viewModels.ProductListViewModel

/**
 * Factory class for creating instances of [ProductListViewModel].
 */
class ProductViewModelFactory : ViewModelProvider.Factory {

    /**
     * Creates a new instance of [ProductListViewModel] with a new instance of [ProductRepository].
     *
     * @param modelClass The class of the ViewModel to create.
     * @return A new instance of [ProductListViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ProductRepository(
            remoteDataSource = RemoteDataSource(),
        )
        return ProductListViewModel(repository) as T
    }
}