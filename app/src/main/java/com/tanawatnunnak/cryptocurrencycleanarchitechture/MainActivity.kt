package com.tanawatnunnak.cryptocurrencycleanarchitechture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.tanawatnunnak.cryptocurrencycleanachitechture.R
import com.tanawatnunnak.cryptocurrencycleanachitechture.databinding.ActivityMainBinding
import com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail.CoinDetailFragment
import com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_list.CoinListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentManager = supportFragmentManager.beginTransaction()

        fragmentManager.add(R.id.mainContainer, CoinListFragment.newInstance(), "1")
            .commit()

    }
}