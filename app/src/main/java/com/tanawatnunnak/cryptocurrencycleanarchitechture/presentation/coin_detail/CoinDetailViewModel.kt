package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.tanawatnunnak.cryptocurrencycleanachitechture.R
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.Constants
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.Resource
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.getString
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model.CoinDetail
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.usecase.GetCoinDetailUseCase
import com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail.adapter.CoinDetailBaseItem


class CoinDetailViewModel(
    application: Application,
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    private val _state = MutableLiveData<CoinDetailState>()
    val state: LiveData<CoinDetailState> = _state

    init {
        _state.postValue(CoinDetailState(isLoading = true))
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)
        }
    }

    fun getCoinDetail(coinId: String) {
        savedStateHandle.set(Constants.PARAM_COIN_ID, coinId)
        getCoinDetailUseCase.execute(coinId).subscribe({ result ->
            when (result) {
                is Resource.Error -> {
                    _state.postValue(
                        _state.value?.copy(
                            error = result.message ?: "An unexpected error"
                        )
                    )
                }
                is Resource.Success -> {
                    val coinDetailItemList = convertDetailToItem(result.data)
                    _state.postValue(
                        _state.value?.copy(
                            coinDetailItemList = coinDetailItemList,
                            isLoading = false
                        )
                    )
                }
            }
        }, { error ->
            _state.postValue(
                _state.value?.copy(
                    error = error.message ?: "An unexpected error"
                )
            )
        })
    }

    private fun convertDetailToItem(detail: CoinDetail?): List<CoinDetailBaseItem> {
        val itemList = ArrayList<CoinDetailBaseItem>()
        if (detail != null) {
            val headerItem = CoinDetailBaseItem.HeaderItem(
                id = 0,
                rank = detail.rank,
                name = detail.name,
                isActive = detail.isActive
            )
            itemList.add(headerItem)
            itemList.add(
                CoinDetailBaseItem.DescriptionItem(
                    itemList.size + 1,
                    description = detail.description
                )
            )

            if (detail.tags.isNotEmpty()) {
                val tagTitle = CoinDetailBaseItem.SectionTitleItem(
                    itemList.size + 1, title = getString(
                        R.string.coin_detail_tag
                    )
                )
                itemList.add(tagTitle)
                itemList.add(CoinDetailBaseItem.TagItem(itemList.size + 1, detail.tags))
            }

            if (detail.team.isNotEmpty()) {
                val teamTitle = CoinDetailBaseItem.SectionTitleItem(
                    itemList.size + 1, title = getString(
                        R.string.coin_detail_team
                    )
                )
                itemList.add(teamTitle)
                detail.team.forEach { team ->
                    itemList.add(
                        CoinDetailBaseItem.TeamItem(
                            itemList.size + 1,
                            name = team.name,
                            jobPosition = team.position
                        )
                    )
                }
            }
        }
        return itemList
    }
}