package com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.base

import io.reactivex.rxjava3.core.Single


abstract class SingleUseCase<R, P>
constructor(
    private val useCaseScheduler: UseCaseScheduler?
) : UseCase<Single<R>, P>() {

    override fun execute(param: P?): Single<R> = super.addScheduler(param).compose { tranfomer ->
        useCaseScheduler?.let {
            tranfomer.observeOn(useCaseScheduler.ui).subscribeOn(useCaseScheduler.io)
        } ?: tranfomer
    }
}
