package com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDetailDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.CoinDataSource

class CoinRemoteDataSourceImp(private val apiService: CoinApi): CoinDataSource {
    override suspend fun getCoins(): List<CoinDto> {
        return apiService.getCoins()
    }

    override suspend fun getCoinDetail(coinId: String): CoinDetailDto {
        return apiService.getCoinDetail(coinId)
    }
}