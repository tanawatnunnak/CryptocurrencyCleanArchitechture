package com.tanawatnunnak.cryptocurrencycleanarchitechture.di

import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.GetCoinDetailUseCase
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.GetCoinsUseCase
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.base.UseCaseScheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    single<Scheduler>(named("ui")) { AndroidSchedulers.mainThread()}
    single<Scheduler>(named("io")) {  Schedulers.io()}

    single { UseCaseScheduler(get(named("ui")), get(named("io"))) }
    single { GetCoinsUseCase(get(), get()) }
    single { GetCoinDetailUseCase(get(), get()) }
}