package com.example.myapplication.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductItemListBinding
import com.example.myapplication.domain.model.Product
import com.example.myapplication.utility.observer
import timber.log.Timber

class ProductListAdapter :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {
    var products: List<Product> by observer(listOf()) {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductItemListBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        if (products.isNotEmpty()) {
            holder.bind(products[position])
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder(private val binding: ProductItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            Timber.tag("TAG").d("product name = %s", product.name)
            binding.productNameTv.text = product.name
            binding.productPriceTv.text = product.price.toString()
            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, "click", Toast.LENGTH_SHORT).show()
            }
        }

    }
}