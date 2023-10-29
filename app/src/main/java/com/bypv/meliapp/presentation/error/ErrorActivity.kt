package com.bypv.meliapp.presentation.error

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bypv.meliapp.R
import com.bypv.meliapp.core.Constants
import com.bypv.meliapp.databinding.ActivityErrorBinding
import com.bypv.meliapp.presentation.ProductList

/**
 * Activity that displays an error view with an image, title, subtitle and a "Try Again" button.
 * The error view is set based on the error type received through the intent extras.
 * The "Try Again" button restarts the ProductList activity.
 */
class ErrorActivity : AppCompatActivity() {

    private val binding: ActivityErrorBinding by lazy {
        ActivityErrorBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initEventError()
    }

    /**
     * Sets up the error view by calling [initTryAgainBtn] and [setView].
     * Catches any exceptions and logs the error message.
     */
    private fun initEventError() {
        try {
            initTryAgainBtn()
            val errorType =
                intent.getSerializableExtra(Constants.ERROR) as TypeError
            setView(errorType)
        } catch (e: Exception) {
            Log.e("ErrorActivity", "${e.message}")
        }
    }

    /**
     * Sets the error view based on the [errorType] received through the intent extras.
     * Updates the image, title and subtitle of the error view accordingly.
     */
    private fun setView(errorType: TypeError) {
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

    /**
     * Initializes the "Try Again" button and sets its click listener to restart the ProductList activity.
     */
    private fun initTryAgainBtn() {
        binding.btnTryAgain.setOnClickListener {
            val intent = Intent(this, ProductList::class.java)
            startActivity(intent)
            finish()
        }
    }
}