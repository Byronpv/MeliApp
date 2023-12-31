package com.bypv.meliapp.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.bypv.meliapp.R
import com.bypv.meliapp.core.Constants
import com.bypv.meliapp.presentation.error.ErrorActivity
import com.bypv.meliapp.presentation.error.TypeError

fun Activity.callErrorActivity(typeError: TypeError) {
    val intent = Intent(this, ErrorActivity::class.java)
    intent.putExtra(Constants.ERROR, typeError)
    startActivity(intent)
}

fun <T : ViewBinding> Activity.setupSearchFilter(binding: T) {
    binding.root.apply {
        findViewById<androidx.appcompat.widget.SearchView>(R.id.sv_product_filter).apply {
            queryHint = getString(R.string.app_search_in_meli)
            setIconifiedByDefault(false)
            findViewById<EditText>(androidx.appcompat.R.id.search_src_text).apply {
                setHintTextColor(ContextCompat.getColor(context, R.color.gray_450))
                setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            isFocusable = false
            clearFocus()
        }
    }
}

fun Activity.isNetworkAvailable(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
    return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}