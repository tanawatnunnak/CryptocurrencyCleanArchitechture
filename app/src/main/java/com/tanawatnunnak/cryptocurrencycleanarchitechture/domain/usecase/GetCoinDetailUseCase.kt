package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase


import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.Resource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.dto.toCoinDetail
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model.CoinDetail
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(private val repository: CoinRepository) :
    UseCase<String, Flow<Resource<CoinDetail>>>() {
    override fun invoke(param: String?): Flow<Resource<CoinDetail>> {
        return flow {
            try {
                emit(Resource.Loading())
                val coins = repository.getCoin(param!!).toCoinDetail()
                emit(Resource.Success(coins))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage))
            } catch (e: IOException) {
                emit(Resource.Error(message = "check Internet"))
            }
        }
    }
}