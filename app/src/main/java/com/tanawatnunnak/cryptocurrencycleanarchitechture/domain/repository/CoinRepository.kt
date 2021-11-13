package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.repository

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDetailDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDto
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface CoinRepository {
    fun getCoins(): Single<Response<List<CoinDto>>>
    fun getCoinDetail(coinId: String): Single<Response<CoinDetailDto>>
}