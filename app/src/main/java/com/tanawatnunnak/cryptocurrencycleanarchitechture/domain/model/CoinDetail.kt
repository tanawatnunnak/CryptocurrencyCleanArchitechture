package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.dto.Team

data class CoinDetail(
val coinId: String,
val    name: String,
val description: String,
val symbol: String,
val rank: Int,
val isActive: Boolean,
val tags: List<String>,
val team :List<Team>
)