package com.example.myapplication.presentation.base

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter <T>(diffUtil: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BaseAdapter.BaseViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind()
    }

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun onBind() = Unit
    }

    abstract class BaseDiffUtilItemCallback<T> : DiffUtil.ItemCallback<T>() {
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem
    }
}