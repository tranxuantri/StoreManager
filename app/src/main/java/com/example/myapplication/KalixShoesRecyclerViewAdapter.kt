package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.myapplication.placeholder.PlaceholderContent.PlaceholderItem
import com.example.myapplication.databinding.ItemKalixShoesBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class KalixShoesRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<KalixShoesRecyclerViewAdapter.KalixShoesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KalixShoesViewHolder {

        return KalixShoesViewHolder(
            ItemKalixShoesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: KalixShoesViewHolder, position: Int) {
        val item = values[position]
        holder.nameView.text = item.id
        holder.desView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class KalixShoesViewHolder(binding: ItemKalixShoesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameView: TextView = binding.shoesName
        val desView: TextView = binding.shoesDes

    }

}