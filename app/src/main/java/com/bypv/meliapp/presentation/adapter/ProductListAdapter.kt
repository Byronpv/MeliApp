package com.bypv.meliapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bypv.meliapp.data.model.ProductModel
import com.bypv.meliapp.databinding.ItemProductBinding

class ProductListAdapter(private var productList: List<ProductModel>, private val onItemClicked: (ProductModel) -> Unit) :
    RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position],onItemClicked)
    }

    override fun getItemCount() = productList.size

    fun clearData() {
        productList = emptyList()
        notifyDataSetChanged()
    }

    fun updateData(newProductList: List<ProductModel>) {
        productList = newProductList
        notifyDataSetChanged()
    }
}