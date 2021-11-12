package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.tanawatnunnak.cryptocurrencycleanachitechture.R
import com.tanawatnunnak.cryptocurrencycleanachitechture.databinding.*
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.getColor

class CoinDetailAdapter(private val tagAdapter: TagAdapter) :
    ListAdapter<CoinDetailBaseItem, RecyclerView.ViewHolder>(CoinDetailDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            CoinDetailType.TYPE_HEADER -> {
                val binding = ItemCoinDetailHeaderBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
                return HeaderViewHolder(binding)
            }
            CoinDetailType.TYPE_DESCRIPTION -> {
                val binding = ItemCoinDetailDescriptionBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
                return DescriptionViewHolder(binding)
            }
            CoinDetailType.TYPE_SECTION_TITLE -> {
                val binding = ItemCoinDetailSectionTitleBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
                return SectionTitleViewHolder(binding)
            }
            CoinDetailType.TYPE_TAG -> {
                val binding = ItemCoinDetailTagBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
                binding.itemCoinDetailTagRcv.apply {
                    val flexboxLayoutManager = FlexboxLayoutManager(context).apply {
                        flexDirection = FlexDirection.ROW
                        justifyContent = JustifyContent.FLEX_START
                    }
                    adapter = tagAdapter
                    layoutManager = flexboxLayoutManager
                }
                return TagViewHolder(binding)
            }
            CoinDetailType.TYPE_TEAM -> {
                val binding = ItemCoinDetailTeamBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
                return TeamViewHolder(binding)
            }
            else -> throw (Exception("type error"))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val item = getItem(position) as CoinDetailBaseItem.HeaderItem
                holder.bind(item)
            }
            is DescriptionViewHolder -> {
                val item = getItem(position) as CoinDetailBaseItem.DescriptionItem
                holder.bind(item)
            }
            is SectionTitleViewHolder -> {
                val item = getItem(position) as CoinDetailBaseItem.SectionTitleItem
                holder.bind(item)
            }
            is TagViewHolder -> {
                val item = getItem(position) as CoinDetailBaseItem.TagItem
                holder.bind(item)
            }
            is TeamViewHolder -> {
                val item = getItem(position) as CoinDetailBaseItem.TeamItem
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    inner class HeaderViewHolder(private val binding: ItemCoinDetailHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: CoinDetailBaseItem.HeaderItem) {
                binding.apply {
                    itemCoinDetailHeaderRankTv.text = "${item.rank}."
                    itemCoinDetailHeaderNameTv.text = item.name
                    val color = when (item.isActive) {
                        true -> getColor(R.color.green_light)
                        else -> getColor(R.color.gray)
                    }
                    itemCoinDetailHeaderActiveTv.setTextColor(color)
                }
        }
    }

    inner class DescriptionViewHolder(private val binding: ItemCoinDetailDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CoinDetailBaseItem.DescriptionItem) {
            binding.itemCoinDetailDescriptionTv.text = item.description
        }
    }

    inner class SectionTitleViewHolder(private val binding: ItemCoinDetailSectionTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CoinDetailBaseItem.SectionTitleItem) {
            binding.itemCoinDetailSectionTitleTv.text = item.title
        }
    }

    inner class TagViewHolder(binding: ItemCoinDetailTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CoinDetailBaseItem.TagItem) {
            tagAdapter.submitList(item.tagList)
        }
    }

    inner class TeamViewHolder(private val binding: ItemCoinDetailTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CoinDetailBaseItem.TeamItem) {
            binding.itemCoinDetailTeamNameTv.text = item.name
            binding.itemCoinDetailTeamJobPositionTv.text = item.jobPosition
        }
    }

    class CoinDetailDiffCallback : DiffUtil.ItemCallback<CoinDetailBaseItem>() {
        override fun areContentsTheSame(
            oldItem: CoinDetailBaseItem,
            newItem: CoinDetailBaseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(
            oldItem: CoinDetailBaseItem,
            newItem: CoinDetailBaseItem
        ): Boolean {
            return if (oldItem is CoinDetailBaseItem.HeaderItem && newItem is CoinDetailBaseItem.HeaderItem) {
                oldItem.name == newItem.name && oldItem.rank == newItem.rank && oldItem.isActive == newItem.isActive
            } else if (oldItem is CoinDetailBaseItem.DescriptionItem && newItem is CoinDetailBaseItem.DescriptionItem) {
                oldItem.description == newItem.description
            } else if (oldItem is CoinDetailBaseItem.SectionTitleItem && newItem is CoinDetailBaseItem.SectionTitleItem) {
                oldItem.title == newItem.title
            } else if (oldItem is CoinDetailBaseItem.TagItem && newItem is CoinDetailBaseItem.TagItem) {
                oldItem.tagList.size == newItem.tagList.size
            } else if (oldItem is CoinDetailBaseItem.TeamItem && newItem is CoinDetailBaseItem.TeamItem) {
                oldItem.name == newItem.name && oldItem.jobPosition == newItem.jobPosition
            } else {
                true
            }
        }
    }
}