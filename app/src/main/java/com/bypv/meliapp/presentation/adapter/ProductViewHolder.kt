package com.bypv.meliapp.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bypv.meliapp.R
import com.bypv.meliapp.data.model.ProductModel
import com.bypv.meliapp.databinding.ItemProductBinding
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.Locale

class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(item: ProductModel, onItemClicked: (ProductModel) -> Unit) {
        binding.apply {
            val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
            val currency: String = format.format(item.price.toDouble().toInt()).replace(".00", "")
            val secureImageUrl = item.thumbnail.replaceFirst("http", "https")
            Picasso.with(root.context).load(secureImageUrl).into(productImg)
            tvTitleProduct.text = item.title
            tvPrice.text = currency
            numofQuotas.text = root.context.getString(R.string.quotes) + (item.price.toDouble() / 12.0).toInt().toString()
            tvSoldBy.text = item.storeName?.let { root.context.getString(R.string.soldBy) + it } ?: ""

        }

        itemView.setOnClickListener { onItemClicked(item) }
    }
}
