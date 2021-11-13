package com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDetailDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.CoinDataSource
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class CoinRemoteDataSourceImp(private val apiService: CoinApi) : CoinDataSource {
    override fun getCoins(): Single<Response<List<CoinDto>>> {
        return apiService.getCoins()
    }

    override fun getCoinDetail(coinId: String): Single<Response<CoinDetailDto>> {
        return apiService.getCoinDetail(coinId)
    }
}