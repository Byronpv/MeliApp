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

class ProductListDetailViewModel(private val repository: ProductRepository): ViewModel() {

    private var _state = MutableStateFlow<Resource<ProductItemDescriptionModel>>(Resource.Loading())
    val state: StateFlow<Resource<ProductItemDescriptionModel>> = _state



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