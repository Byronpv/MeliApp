package com.bypv.meliapp.presentation

import android.app.Activity
import android.content.Intent
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.bypv.meliapp.R
import com.bypv.meliapp.presentation.error.ErrorActivity
import com.bypv.meliapp.presentation.error.TypeError

fun Activity.callErrorActivity(typeError: TypeError) {
    val intent = Intent(this, ErrorActivity::class.java)
    intent.putExtra("typeError", typeError)
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