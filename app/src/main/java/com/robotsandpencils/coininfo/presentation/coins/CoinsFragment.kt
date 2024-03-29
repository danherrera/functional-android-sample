package com.robotsandpencils.coininfo.presentation.coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.robotsandpencils.coininfo.R
import kotlinx.android.synthetic.main.fragment_coins.*
import org.koin.android.ext.android.inject

class CoinsFragment : Fragment() {

    private val viewModel: CoinsViewModel by inject()
    private val adapter = CoinRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coins, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener { viewModel.getData() }
        coinRecyclerView.adapter = adapter
        adapter.onClickCoin = {
            findNavController().navigate(
                CoinsFragmentDirections.actionCoinsFragmentToCoinDetailsFragment(
                    it.id
                )
            )
        }
        viewModel.getData()
        viewModel.coins.observe(this, Observer {
            adapter.coins = it ?: emptyList()
        })
        viewModel.isLoading.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = it ?: false
        })
    }
}