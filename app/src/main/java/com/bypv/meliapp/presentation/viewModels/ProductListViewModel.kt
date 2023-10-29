package com.bypv.meliapp.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypv.meliapp.core.Resource
import com.bypv.meliapp.data.model.ProductResponse
import com.bypv.meliapp.domain.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel class for the product list screen.
 *
 * @param repository The repository used to fetch the product data.
 */
class ProductListViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _state = MutableStateFlow<Resource<ProductResponse>>(Resource.Loading())
    val state: StateFlow<Resource<ProductResponse>> = _state

    /**
     * Searches for products with the given category ID.
     *
     * @param categoryId The ID of the category to search for.
     */
    fun searchCategoryId(categoryId: String) {
        viewModelScope.launch {
            try {
                _state.value = Resource.Success(repository.getProducts(categoryId))
            } catch (e: Exception) {
                Log.e("Error connection", "${e.message}")
                _state.value = Resource.Failure(e)
            }
        }
    }
}