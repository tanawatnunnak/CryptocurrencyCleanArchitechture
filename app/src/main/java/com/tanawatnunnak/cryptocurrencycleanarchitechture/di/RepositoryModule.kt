package com.tanawatnunnak.cryptocurrencycleanarchitechture.di

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.repository.CoinRepositoryImp
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.repository.CoinRepository
import org.koin.dsl.module


val repositoryModule = module {
    single<CoinRepository> { CoinRepositoryImp(get()) }
}
