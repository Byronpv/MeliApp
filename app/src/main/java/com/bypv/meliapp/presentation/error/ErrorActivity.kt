package com.bypv.meliapp.presentation.error

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bypv.meliapp.R
import com.bypv.meliapp.core.Constants
import com.bypv.meliapp.databinding.ActivityErrorBinding
import com.bypv.meliapp.presentation.ProductList

class ErrorActivity : AppCompatActivity(){

    private val binding: ActivityErrorBinding by lazy {
        ActivityErrorBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        try {
            initTryAgainBtn()
            val errorType =
                intent.getSerializableExtra(Constants.ERROR) as TypeError
            setView(errorType)
        } catch (e: Exception) {
            Log.e("ErrorActivity", "${e.message}")
        }
    }

    private fun setView(errorType: TypeError) {
        Log.w("ErrorActivity", "ErrorType: $errorType")
        when (errorType) {
            TypeError.NO_INTERNET_ERROR -> {
                binding.errorImage.setImageResource(R.drawable.no_internet)
                binding.errorTitle.text = getString(R.string.app_error_internet_failed)
                binding.errorSubtitle.text = getString(R.string.app_error_internet_failed_subtitle)
            }
            TypeError.DEFAULT_ERROR_VIEW -> {
                binding.errorImage.setImageResource(R.drawable.app_error_default)
                binding.errorTitle.text = getString(R.string.app_error_something)
                binding.errorSubtitle.text = getString(R.string.app_error_something_subtitle)
            }
        }
    }

    private fun initTryAgainBtn() {
        binding.btnTryAgain.setOnClickListener {
            val intent = Intent(this,ProductList::class.java)
            startActivity(intent)
        }
    }
}