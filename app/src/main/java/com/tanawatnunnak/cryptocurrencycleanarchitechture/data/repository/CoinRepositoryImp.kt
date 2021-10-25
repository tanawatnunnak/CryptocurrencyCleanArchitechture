package com.tanawatnunnak.cryptocurrencycleanarchitechture.data.repository

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.CoinApi
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.dto.CoinDetailDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.dto.CoinDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.repository.CoinRepository
import javax.inject.Inject


class CoinRepositoryImp @Inject constructor(
    private val api: CoinApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoin(coinId: String): CoinDetailDto {
        return api.getCoin(coinId)
    }
}