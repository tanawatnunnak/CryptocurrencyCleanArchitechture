package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.Resource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private val _state = MutableLiveData<CoinListState>()

    private fun getCoins(){
        getCoinsUseCase().onEach { result ->
            when(result){
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "An unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinListState(coinList = result.data ?: emptyList())
                }
            }
        }
    }
}