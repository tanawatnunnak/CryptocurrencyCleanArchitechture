package com.tanawatnunnak.cryptocurrencycleanarchitechture.di

import com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail.CoinDetailViewModel
import com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_list.CoinListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CoinListViewModel(get()) }
    viewModel { CoinDetailViewModel(get(), get(), get()) }
}