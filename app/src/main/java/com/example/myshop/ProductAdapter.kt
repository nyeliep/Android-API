package com.example.myshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.Products
import com.example.myshop.databinding.ProductBinding
import com.squareup.picasso.Picasso

class ProductAdapt(var productList: List<Products>) : RecyclerView.Adapter<ProductAdapt.ProductViewHolder>() {

    fun updateProducts(newProducts: List<Products>) {
        productList = newProducts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    inner class ProductViewHolder(private val binding: ProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Products) {
            binding.apply {
                Picasso.get().load(product.thumbnail).into(thumbnail)
                productName.text = product.title
                description.text = product.description
                price.text = product.price.toString()
                rating.text = product.ratings.toString()
                stock.text = product.stock.toString()
                category.text = product.category
            }
        }
    }
}
