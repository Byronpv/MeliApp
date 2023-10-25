package com.bypv.meliapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bypv.meliapp.R
import com.bypv.meliapp.core.Constants
import com.bypv.meliapp.core.Resource
import com.bypv.meliapp.data.model.ProductModel
import com.bypv.meliapp.databinding.ActivityProductListBinding
import com.bypv.meliapp.presentation.adapter.ProductListAdapter
import com.bypv.meliapp.presentation.error.ErrorActivity
import com.bypv.meliapp.presentation.error.TypeError
import com.bypv.meliapp.presentation.viewModels.ProductListViewModel
import com.bypv.meliapp.presentation.viewModels.viewModelFactories.ProductViewModelFactory
import kotlinx.coroutines.launch

class ProductList : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private val binding: ActivityProductListBinding by lazy { ActivityProductListBinding.inflate(layoutInflater) }
    private val viewModel: ProductListViewModel by viewModels { ProductViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.topBarId.svProductFilter.setOnQueryTextListener(this)
        setContentView(binding.root)
        setupSearchFilter(binding)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect() { state ->
                    when (state) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rvProducts.adapter = ProductListAdapter(state.data.results, ::onItemClick)
                        }

                        is Resource.Failure -> {
                            callErrorActivity(TypeError.DEFAULT_ERROR_VIEW)
                        }
                    }
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val input = !query.isNullOrEmpty()
        if (input) {
            query?.let {
                (viewModel.searchCategoryId(it))
            }
        }
        return input
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun onItemClick(productItem: ProductModel) {
        val intentToProductDetailActivity = Intent(this, ProductListDetail::class.java)
            .putExtra(Constants.PRODUCT_ITEM, productItem)
        startActivity(intentToProductDetailActivity)
    }
}