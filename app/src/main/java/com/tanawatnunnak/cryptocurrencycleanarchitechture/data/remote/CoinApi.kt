package com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDetailDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDto
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("/v1/coins")
     fun getCoins(): Single<Response<List<CoinDto>>>

    @GET("/v1/coins/{coinId}")
     fun getCoinDetail(@Path("coinId") coinId : String): Single<Response<CoinDetailDto>>
}