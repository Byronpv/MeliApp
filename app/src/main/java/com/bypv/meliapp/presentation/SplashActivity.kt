package com.bypv.meliapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bypv.meliapp.R
import com.bypv.meliapp.databinding.ActivityProductListDetailBinding
import com.bypv.meliapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        startProductList()
    }

    private fun startProductList() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, ProductList::class.java))
            finish()
        }, 2000)
    }
}
