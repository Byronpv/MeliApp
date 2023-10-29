package com.bypv.meliapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bypv.meliapp.core.Constants
import com.bypv.meliapp.core.Resource
import com.bypv.meliapp.data.model.ProductModel
import com.bypv.meliapp.databinding.ActivityProductListBinding
import com.bypv.meliapp.presentation.adapter.ProductListAdapter
import com.bypv.meliapp.presentation.error.TypeError
import com.bypv.meliapp.presentation.viewModels.ProductListViewModel
import com.bypv.meliapp.presentation.viewModels.viewModelFactories.ProductViewModelFactory
import kotlinx.coroutines.launch

class ProductList : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private val binding: ActivityProductListBinding by lazy { ActivityProductListBinding.inflate(layoutInflater) }
    private val viewModel: ProductListViewModel by viewModels { ProductViewModelFactory() }
    lateinit var productAdapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.topBarId.svProductFilter.setOnQueryTextListener(this)
        setContentView(binding.root)
        setupSearchFilter(binding)
        setupRecyclerView()
        setUpObserverForState()


    }

    private fun setUpObserverForState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect() { state ->
                    when (state) {
                        is Resource.Loading -> {
                        }

                        is Resource.Success -> {
                            binding.apply {
                                shimmerLayout.visibility = View.GONE
                                shimmerLayout.stopShimmer()
                                productAdapter.updateData(state.data.results)

                            }
                        }

                        is Resource.Failure -> {
                            if (!isNetworkAvailable()) {
                                callErrorActivity(TypeError.NO_INTERNET_ERROR)
                            } else {
                                callErrorActivity(TypeError.DEFAULT_ERROR_VIEW)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val input = !query.isNullOrEmpty()

        if (input) {
            binding.shimmerLayout.visibility = View.VISIBLE
            binding.shimmerLayout.startShimmer()
            productAdapter.clearData()

            query?.let {
                viewModel.searchCategoryId(it)
            }
            hideKeyboard()
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

    private fun setupRecyclerView() {
        productAdapter = ProductListAdapter(emptyList(), ::onItemClick)
        binding.rvProducts.adapter = productAdapter
    }
}