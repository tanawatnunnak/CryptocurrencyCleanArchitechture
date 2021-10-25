package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail

import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model.CoinDetail

data class CoinDetailState(val isLoading: Boolean = false,
                           val coinDetail: CoinDetail? = null,
                           val error: String = "")