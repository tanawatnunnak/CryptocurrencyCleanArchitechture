package com.tanawatnunnak.cryptocurrencycleanarchitechture.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDetailDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.CoinDto
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.CoinApi
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.CoinRemoteDataSourceImp
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.CoinDataSource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.repository.CoinRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)

class CoinRepositoryImpTest {

    @Mock
    private lateinit var service: CoinApi
    private lateinit var remote: CoinDataSource
    private lateinit var repository: CoinRepository

    @Before
    fun setUp() {
        remote = CoinRemoteDataSourceImp(service)
        repository = CoinRepositoryImp(remote)
    }

    @Test
    fun getCoins() {
        val coinList = mock<List<CoinDto>>()
        val response = Response.success(coinList)
        whenever(remote.getCoins()).thenReturn(Single.just(response))

        repository.getCoins().test()
            .assertResult(response)
    }

    @Test
    fun getCoinDetail() {
        val coinId = "1"
        val coinDetail = mock<CoinDetailDto>()
        val response = Response.success(coinDetail)
        whenever(remote.getCoinDetail(coinId)).thenReturn(Single.just(response))

        repository.getCoinDetail(coinId).test()
            .assertResult(response)
    }
}