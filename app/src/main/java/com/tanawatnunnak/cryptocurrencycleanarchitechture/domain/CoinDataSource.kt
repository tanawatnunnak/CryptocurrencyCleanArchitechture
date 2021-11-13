package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDetailDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDto

import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface CoinDataSource {
    fun getCoins(): Single<Response<List<CoinDto>>>
    fun getCoinDetail(coinId: String): Single<Response<CoinDetailDto>>
}