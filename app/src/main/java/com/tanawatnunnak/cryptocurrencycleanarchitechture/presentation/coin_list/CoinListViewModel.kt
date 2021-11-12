package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.Resource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.GetCoinsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class CoinListViewModel(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private val _state = MutableLiveData<CoinListState>()
    val state : LiveData<CoinListState> = _state
    fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "An unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinListState(coinList = result.data ?: emptyList(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}