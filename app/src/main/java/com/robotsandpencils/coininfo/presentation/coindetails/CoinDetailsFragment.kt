package com.robotsandpencils.coininfo.presentation.coindetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.robotsandpencils.coininfo.R
import com.robotsandpencils.coininfo.presentation.common.StartEndTextRecyclerViewAdapter
import com.robotsandpencils.coininfo.presentation.common.StartEndTextViewModel
import kotlinx.android.synthetic.main.fragment_coin_details.*
import org.koin.android.ext.android.inject
import java.math.RoundingMode

class CoinDetailsFragment : Fragment() {

    private val viewModel: CoinDetailsViewModel by inject()
    private val args: CoinDetailsFragmentArgs by navArgs()
    private val adapter =
        StartEndTextRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coin_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        marketsRecyclerView.adapter = adapter
        viewModel.getMarkets(args.coinId)
        viewModel.state.observe(this, Observer(::setState))
    }

    fun setState(state: CoinDetailsState) {
        adapter.viewModels = state.markets.map {
            StartEndTextViewModel(
                it.name,
                "${it.price.setScale(
                    2,
                    RoundingMode.HALF_UP
                )} ${it.quoteCurrency}/${it.baseCurrency}"
            )
        }
    }
}
