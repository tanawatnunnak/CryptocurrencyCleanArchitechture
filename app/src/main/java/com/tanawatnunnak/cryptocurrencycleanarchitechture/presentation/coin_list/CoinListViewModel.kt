package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.Resource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.GetCoinsUseCase


class CoinListViewModel(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = MutableLiveData<CoinListState>()
    val state: LiveData<CoinListState> = _state

    init {
        _state.postValue(CoinListState(isLoading = true))
    }

    fun getCoins() {
        getCoinsUseCase.execute().subscribe({ result ->
            when (result) {
                is Resource.Error -> {
                    _state.postValue(
                        _state.value?.copy(
                            error = result.message ?: "An unexpected error"
                        )
                    )
                }
                is Resource.Success -> {
                    _state.postValue(
                        _state.value?.copy(
                            coinList = result.data ?: emptyList(),
                            isLoading = false
                        )
                    )

                }
            }
        }, { error ->
            _state.postValue(_state.value?.copy(error = error.message ?: "An unexpected error"))
        })
    }
}