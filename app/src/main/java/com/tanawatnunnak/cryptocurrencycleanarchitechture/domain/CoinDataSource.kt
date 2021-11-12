package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDetailDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDto

interface CoinDataSource {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinDetail(coinId: String): CoinDetailDto
}