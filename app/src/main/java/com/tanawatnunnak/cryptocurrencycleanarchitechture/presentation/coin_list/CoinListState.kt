package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_list

import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model.Coin


data class CoinListState(val isLoading: Boolean = false,
                         val coinList: List<Coin> = emptyList(),
                         val error: String = "")