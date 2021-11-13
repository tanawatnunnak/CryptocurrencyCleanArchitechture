package com.tanawatnunnak.cryptocurrencycleanarchitechture.data.repository

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDetailDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.CoinDataSource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.repository.CoinRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class CoinRepositoryImp(private val remoteDataSource: CoinDataSource) : CoinRepository {
    override  fun getCoins(): Single<Response<List<CoinDto>>> {
        return remoteDataSource.getCoins()
    }

    override  fun getCoinDetail(coinId: String): Single<Response<CoinDetailDto>> {
        return remoteDataSource.getCoinDetail(coinId)
    }
}