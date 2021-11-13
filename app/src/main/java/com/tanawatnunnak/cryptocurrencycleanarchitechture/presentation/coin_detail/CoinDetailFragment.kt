package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanawatnunnak.cryptocurrencycleanachitechture.databinding.FragmentCoinDetialBinding
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.BaseFragment
import com.tanawatnunnak.cryptocurrencycleanarchitechture.common.setVisibility
import com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail.adapter.CoinDetailAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinDetailFragment : BaseFragment<FragmentCoinDetialBinding>() {

    private val coinDetailAdapter: CoinDetailAdapter by inject()
    private val coinDetailViewModel: CoinDetailViewModel by viewModel()

    override fun getViewBinding() = FragmentCoinDetialBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coinDetailViewModel.getCoinDetail(getCoinIdArgument())
    }

    override fun initView() {
        binding?.let { view ->
            view.coinDetailBackIv.setOnClickListener { parentFragmentManager.popBackStack() }
            view.coinDetailRcv.apply {
                adapter = coinDetailAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            }
        }
    }

    override fun initObservable() {
        coinDetailViewModel.state.observe(viewLifecycleOwner, ::updateUI)
    }

    private fun getCoinIdArgument() = arguments?.getString(ARG_COIN_ID) ?: ""

    private fun updateUI(state: CoinDetailState) {
        binding?.coinDetailLoading?.setVisibility(state.isLoading)
        coinDetailAdapter.submitList(state.coinDetailItemList)
    }

    companion object {
        private var ARG_COIN_ID: String? = null
        fun newInstance(coinId: String) =
            CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_COIN_ID, coinId)
                }
            }
    }
}