package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.base

abstract class UseCase<R, P> {
    abstract fun execute(param: P? = null): R

    protected fun addScheduler (param: P?) : R = execute(param)
}