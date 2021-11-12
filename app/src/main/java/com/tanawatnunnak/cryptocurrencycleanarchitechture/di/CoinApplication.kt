package com.tanawatnunnak.cryptocurrencycleanarchitechture.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class CoinApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(applicationContext)
            modules(listOf(networkModule, repositoryModule, useCaseModule, viewModelModule, adapterModule))
        }
    }
}