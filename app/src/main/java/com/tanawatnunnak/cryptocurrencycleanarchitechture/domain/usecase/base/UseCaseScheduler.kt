package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.base

import io.reactivex.rxjava3.core.Scheduler

data class UseCaseScheduler(val ui: Scheduler, val io: Scheduler)
