package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail

import com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail.adapter.CoinDetailBaseItem

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetailItemList: List<CoinDetailBaseItem> = listOf(),
    val error: String = ""
)