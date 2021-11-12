package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase

import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.Resource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.toCoin
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model.Coin
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinsUseCase (private val repository: CoinRepository) :
    UseCase<Nothing, Flow<Resource<List<Coin>>>>() {

    override fun invoke(param: Nothing?): Flow<Resource<List<Coin>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val coins = repository.getCoins().map { it.toCoin() }
                emit(Resource.Success(coins))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage))
            } catch (e: IOException) {
                emit(Resource.Error(message = "check Internet"))
            }
        }
    }
}