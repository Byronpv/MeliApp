package com.bypv.meliapp.presentation.error

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bypv.meliapp.R
import com.bypv.meliapp.databinding.ActivityErrorBinding
import com.bypv.meliapp.presentation.setupSearchFilter
import java.lang.NullPointerException

class ErrorActivity : AppCompatActivity() {


    private val binding: ActivityErrorBinding by lazy {
        ActivityErrorBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupSearchFilter(binding)
        try {
            initTryAgainBtn()
            val errorType =
                intent.getSerializableExtra(getString(R.string.app_something_wrong)) as TypeError
            setView(errorType)
        } catch (e: NullPointerException) {
            Log.e("ErrorActivity",e.toString())
        }
    }

    private fun setView(errorType: TypeError) {
        when(errorType){
            TypeError.NO_INTERNET_ERROR -> {
                binding.errorImage.setImageResource(R.drawable.app_error_no_internet)
                binding.errorTitle.text = getString(R.string.app_error_internet_failed)
                binding.errorSubtitle.text = getString(R.string.app_error_internet_failed_subtitle)
            }

            else -> {}
        }
    }

    private fun initTryAgainBtn() {
        binding.btnTryAgain.setOnClickListener {
            finish()
        }
    }

}