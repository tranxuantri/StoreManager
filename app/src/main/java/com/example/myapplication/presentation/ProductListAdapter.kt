package com.example.myapplication.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductItemListBinding
import com.example.myapplication.domain.model.Product
import com.example.myapplication.utility.observer

class ProductListAdapter(list: Map<String, Product>) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {
    var products: List<Product> by observer(listOf()) {
        notifyDataSetChanged()
    }
    private var productList: Map<String, Product> = list
    private var listKey = productList.keys.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductItemListBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        if (productList.isNotEmpty() && listKey.isNotEmpty()) {
            holder.bind(productList.getValue(listKey[position]))
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(list: Map<String, Product>) {
        this.productList = list
        listKey = productList.keys.toList()
    }

    class ProductViewHolder(private val binding: ProductItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            Log.d("TAG","product name = ${product.name}")
            binding.productNameTv.text = product.name
            binding.productPriceTv.text = product.price.toString()
            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, "click", Toast.LENGTH_SHORT).show()
            }
        }

    }
}