package com.tanawatnunnak.cryptocurrencycleanarchitechture.di

import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.GetCoinDetailUseCase
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.GetCoinsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCoinsUseCase(get()) }
    single { GetCoinDetailUseCase(get()) }
}