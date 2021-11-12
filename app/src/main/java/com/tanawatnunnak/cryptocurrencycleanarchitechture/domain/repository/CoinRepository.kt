package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.repository

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDetailDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinDetail(coinId: String): CoinDetailDto
}