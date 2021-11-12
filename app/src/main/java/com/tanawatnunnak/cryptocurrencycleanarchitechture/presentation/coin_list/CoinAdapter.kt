package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tanawatnunnak.cryptocurrencycleanachitechture.R
import com.tanawatnunnak.cryptocurrencycleanachitechture.databinding.ItemCoinBinding
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.getColor
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model.Coin

class CoinAdapter(private val onItemClick: (coin: Coin) -> Unit) :
    ListAdapter<Coin, CoinAdapter.CoinViewHolder>(CoinDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CoinViewHolder(private val binding: ItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Coin) {
            binding.apply {
                itemCoinRankTv.text = "${item.rank}."
                itemCoinNameTv.text = item.name
                val color = when (item.isActive) {
                    true -> getColor(R.color.green_light)
                    else -> getColor(R.color.gray)
                }
                itemCoinActiveTv.setTextColor(color)
            }
            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    class CoinDiffCallback : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.name == newItem.name && oldItem.isActive == newItem.isActive
        }

    }
}