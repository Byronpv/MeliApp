package com.bypv.meliapp.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bypv.meliapp.R
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

    var productItems: ProductModel? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        productItems = intent.getSerializableExtra(Constants.PRODUCT_ITEM) as ProductModel


        productItems?.let { productId ->
            binding.apply {
                val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
                val currency: String = format.format(productItems!!.price.toDouble()).replace(".00", "")
                viewModel.getDescription(productId.id)
                tvTitleDetail.text = productItems!!.title
                tvTitleConditions.text = productItems!!.conditionModel
                tvPriceDetail.text = currency
                btnBackDetail.setOnClickListener {
                    onBackPressed()
                }

            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is Resource.Loading -> {
                        }

                        is Resource.Success -> {
                            with(binding) {
                                tvDescriptionDetailRS.text = state.data.plain_text
                            }
                        }

                        is Resource.Failure -> {
                            callErrorActivity(TypeError.DEFAULT_ERROR_VIEW)
                        }
                    }

                }
            }
        }
    }
}