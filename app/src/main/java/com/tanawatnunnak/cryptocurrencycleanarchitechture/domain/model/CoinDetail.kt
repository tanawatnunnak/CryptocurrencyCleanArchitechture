package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.Tag
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.Team

data class CoinDetail(
    val coinId: String,
    val    name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<Tag>,
    val team :List<Team>
)