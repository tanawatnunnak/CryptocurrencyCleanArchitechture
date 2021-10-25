package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.repository

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.dto.CoinDetailDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoin(coinId: String): CoinDetailDto
}