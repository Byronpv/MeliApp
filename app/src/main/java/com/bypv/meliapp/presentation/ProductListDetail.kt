package com.bypv.meliapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bypv.meliapp.core.Constants
import com.bypv.meliapp.core.Resource
import com.bypv.meliapp.data.model.ProductModel
import com.bypv.meliapp.databinding.ActivityProductListDetailBinding
import com.bypv.meliapp.presentation.error.TypeError
import com.bypv.meliapp.presentation.viewModels.ProductListDetailViewModel
import com.bypv.meliapp.presentation.viewModels.viewModelFactories.ProductDetailViewModelFactory
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.Locale
import kotlinx.coroutines.launch

class ProductListDetail : AppCompatActivity() {

    private val binding: ActivityProductListDetailBinding by lazy {
        ActivityProductListDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: ProductListDetailViewModel by viewModels { ProductDetailViewModelFactory() }

    private var productItems: ProductModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        productItems = intent.getSerializableExtra(Constants.PRODUCT_ITEM) as? ProductModel

        productItems.let {
            getProductsItems()
            setUpObserverForState()
        }.run { Log.e("ProductListDetail", "Error al cargar los productos") }
    }

    private fun setUpObserverForState() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is Resource.Loading -> {
                        }

                        is Resource.Success -> {
                            with(binding) {
                                val thumbnailSecurity = productItems!!.thumbnail.replaceFirst("http", "https")
                                Picasso.with(root.context)
                                    .load(thumbnailSecurity)
                                    .into(ivDetail)
                                tvDescriptionDetailRS.text = state.data.plain_text
                            }
                        }

                        is Resource.Failure -> {
                            if (isNetworkAvailable()) {
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

    /**
     * The getProductsItems method sets up the UI with the product details.
     */
    private fun getProductsItems() {

        productItems.let { productId ->
            binding.apply {
                val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
                val currency: String = format.format(productItems?.price?.toDouble()?.toInt() ?: 0).replace(".00", "")
                productId?.id?.let { viewModel.getDescription(it) }
                tvTitleDetail.text = productItems?.title ?: ""
                tvTitleConditions.text = productItems?.conditionModel ?: ""
                tvPriceDetail.text = currency
                btnBackDetail.setOnClickListener {
                    onBackPressed()
                }

            }
        }
    }
}