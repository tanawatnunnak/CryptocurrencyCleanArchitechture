package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_list

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanawatnunnak.cryptocurrencycleanachitechture.R
import com.tanawatnunnak.cryptocurrencycleanachitechture.databinding.FragmentCoinsListBinding
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.BaseFragment
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.setVisibility
import com.tanawatnunnak.cryptocurrencycleanarchitechture.domain.model.Coin
import com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail.CoinDetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinListFragment : BaseFragment<FragmentCoinsListBinding>() {

    private val onCoinItemClick: (Coin) -> Unit = { coin ->
        openCoinDetail(coin.id)
    }

    private val viewModel: CoinListViewModel by viewModel()
    private val coinAdapter = CoinAdapter(onCoinItemClick)

    override fun getViewBinding() = FragmentCoinsListBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCoins()
    }

    override fun initView() {
        binding?.coinsRcv?.apply {
            adapter = coinAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun updateUI(state: CoinListState) {
        binding?.coinsLoading?.setVisibility(state.isLoading)
        coinAdapter.submitList(state.coinList)
        if (state.error != "") {
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun initObservable() {
        viewModel.state.observe(viewLifecycleOwner, ::updateUI)
    }

    private fun openCoinDetail(coinId: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, CoinDetailFragment.newInstance(coinId))
            .addToBackStack("")
            .commit()
    }

    companion object {
        fun newInstance() = CoinListFragment()
    }
}
