package com.example.myapplication.presentation.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.databinding.ProductCartItemBinding
import com.example.myapplication.databinding.ProductItemListBinding
import com.example.myapplication.domain.model.Product
import com.example.myapplication.presentation.base.BaseAdapter
import com.example.myapplication.presentation.product.ProductViewModel

class ShoppingCartAdapter : BaseAdapter<Product>(ItemDiffUntil()) {
    internal var onItemClick: (product: Product, context: Context) -> Unit = { product: Product, context: Context ->
        Toast.makeText(context, "Item $product", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        ItemViewHolder(
            ProductCartItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )

    inner class ItemViewHolder(private val binding: ProductCartItemBinding) : BaseViewHolder(binding.root) {
        init {
            binding.productRemoveBtn.setOnClickListener {
                onItemClick.invoke(getItem(bindingAdapterPosition), binding.root.context)
            }
        }

        override fun onBind() {
            getItem(bindingAdapterPosition).let {
                itemView.run {
                    binding.productNameTv.text = it.name
                    binding.productPriceTv.text = it.price.toString()
                    binding.productQuantity.max = it.quantityInStock
                }
            }
        }

    }

    private class ItemDiffUntil : BaseDiffUtilItemCallback<Product>()

}