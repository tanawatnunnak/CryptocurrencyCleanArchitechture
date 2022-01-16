package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase

import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.Resource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.toResource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.toCoin
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model.Coin
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.repository.CoinRepository
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.base.SingleUseCase
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.base.UseCaseScheduler
import io.reactivex.rxjava3.core.Single

class GetCoinsUseCase(
    scheduler: UseCaseScheduler,
    private val repository: CoinRepository
) : SingleUseCase<Resource<List<Coin>>, Nothing>(scheduler) {

    override fun execute(param: Nothing?): Single<Resource<List<Coin>>> {
        return repository.getCoins()
            .map { response ->
                response.toResource(mapper = { coinList ->
                    coinList.map { it.toCoin() }
                })
            }
    }
}