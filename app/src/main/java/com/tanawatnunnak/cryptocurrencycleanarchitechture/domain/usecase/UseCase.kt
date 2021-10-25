package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase

abstract class UseCase<T, R> {
    abstract operator fun invoke(param: T? = null): R
}
