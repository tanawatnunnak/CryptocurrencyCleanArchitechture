package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase


import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.Resource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.toResource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.toCoinDetail
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model.CoinDetail
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.repository.CoinRepository
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.base.SingleUseCase
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.base.UseCaseScheduler
import io.reactivex.rxjava3.core.Single

class GetCoinDetailUseCase(
    scheduler: UseCaseScheduler,
    private val repository: CoinRepository
) :
    SingleUseCase<Resource<CoinDetail>, String>(scheduler) {

    override fun execute(param: String?): Single<Resource<CoinDetail>> {
        return repository.getCoinDetail(param ?: "")
            .map { response ->
                response.toResource {
                    it.toCoinDetail() }
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()?.toCoinDetail())
                } else {
                    Resource.Error(message = response.message())
                }
            }
    }
}