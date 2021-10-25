package com.tanawatnunnak.cryptocurrencycleanarchitechture.di

import com.google.gson.Gson
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.Constants
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.CoinApi
import com.tanawatnunnak.cryptocurrencycleanachitechture.data.repository.CoinRepositoryImp
import com.tanawatnunnak.cryptocurrencycleanachitechture.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCoinApi(): com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.CoinApi {
        return Retrofit.Builder()
            .baseUrl(com.tanawatnunnak.cryptocurrencycleanarchitechture.common.Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.CoinApi): CoinRepository{
        return CoinRepositoryImp(api)
    }

}