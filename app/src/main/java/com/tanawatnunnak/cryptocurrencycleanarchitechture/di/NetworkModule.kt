package com.tanawatnunnak.cryptocurrencycleanarchitechture.di

import com.tanawatnunnak.cryptocurrencycleanachitechture.BuildConfig
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.Constants
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.CoinApi
import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.remote.CoinRemoteDataSourceImp
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.CoinDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit() }
    factory { provideCoinApi(get()) }
    single<CoinDataSource> { CoinRemoteDataSourceImp(get()) }
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .client(createLogInterceptor())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createLogInterceptor(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)
    }
    return httpClient.build()
}

fun provideCoinApi(retrofit: Retrofit): CoinApi {
    return retrofit.create(CoinApi::class.java)
}