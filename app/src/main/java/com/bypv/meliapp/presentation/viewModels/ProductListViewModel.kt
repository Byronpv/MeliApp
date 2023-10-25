package com.bypv.meliapp.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypv.meliapp.data.ProductRepository
import com.bypv.meliapp.data.model.ProductResponse
import com.bypv.meliapp.core.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductListViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _state = MutableStateFlow<Resource<ProductResponse>>(Resource.Loading())
    val state: StateFlow<Resource<ProductResponse>> = _state

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