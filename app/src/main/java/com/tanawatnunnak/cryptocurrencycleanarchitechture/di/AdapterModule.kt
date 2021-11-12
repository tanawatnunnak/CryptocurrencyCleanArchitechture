package com.tanawatnunnak.cryptocurrencycleanarchitechture.di

import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model.Coin
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model.CoinDetail
import com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail.adapter.CoinDetailAdapter
import com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail.adapter.TagAdapter
import com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_list.CoinAdapter
import org.koin.dsl.module

val adapterModule = module {
   // single { (onClick: (Coin) -> Unit) -> CoinAdapter(onClick) }
    factory { TagAdapter() }
    factory { CoinDetailAdapter(get()) }
}