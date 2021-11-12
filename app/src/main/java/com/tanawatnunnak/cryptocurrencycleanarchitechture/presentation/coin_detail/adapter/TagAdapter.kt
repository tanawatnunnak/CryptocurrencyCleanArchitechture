package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tanawatnunnak.cryptocurrencycleanachitechture.databinding.ItemTagBinding
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.Tag

class TagAdapter : ListAdapter<Tag, TagAdapter.TagViewHolder>(TagDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.binding.itemTagTitletv.text = getItem(position).name
    }

    inner class TagViewHolder(val binding: ItemTagBinding) : RecyclerView.ViewHolder(binding.root)

    class TagDiffCallback : DiffUtil.ItemCallback<Tag>() {
        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name
        }

    }
}