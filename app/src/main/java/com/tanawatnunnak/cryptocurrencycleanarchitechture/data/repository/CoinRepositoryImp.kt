package com.tanawatnunnak.cryptocurrencycleanarchitechture.data.repository

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDetailDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.CoinDataSource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.repository.CoinRepository

class CoinRepositoryImp(private val remoteDataSource: CoinDataSource) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return remoteDataSource.getCoins()
    }

    override suspend fun getCoinDetail(coinId: String): CoinDetailDto {
        return remoteDataSource.getCoinDetail(coinId)
    }
}