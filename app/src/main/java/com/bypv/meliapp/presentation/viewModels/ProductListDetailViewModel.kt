package com.bypv.meliapp.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypv.meliapp.core.Resource
import com.bypv.meliapp.data.model.ProductItemDescriptionModel
import com.bypv.meliapp.domain.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel class for the Product List Detail screen.
 * @param repository the repository to fetch data from.
 */
class ProductListDetailViewModel(private val repository: ProductRepository): ViewModel() {

    /**
     * The current state of the resource being fetched.
     */
    private var _state = MutableStateFlow<Resource<ProductItemDescriptionModel>>(Resource.Loading())
    val state: StateFlow<Resource<ProductItemDescriptionModel>> = _state

    /**
     * Fetches the description of a product with the given ID.
     * @param productId the ID of the product to fetch the description for.
     */
    fun getDescription(productId: String){
        viewModelScope.launch {
            try {
                _state.value = Resource.Success(repository.getDescriptionProduct(productId))
            } catch (e: Exception) {
                Log.e("Error connection", "${e.message}")
                _state.value = Resource.Failure(e)
            }
        }
    }

    /**
     * Fetches the pictures of a product with the given ID.
     * @param productId the ID of the product to fetch the pictures for.
     */
    fun getPictures(productId: String){
        viewModelScope.launch {
            try {
                _state.value = Resource.Success(repository.getPicturesProduct(productId))
            } catch (e: Exception) {
                Log.e("Error connection", "${e.message}")
                _state.value = Resource.Failure(e)
            }
        }
    }

}