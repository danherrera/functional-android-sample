package com.robotsandpencils.coininfo.presentation.coins

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robotsandpencils.coininfo.R
import com.robotsandpencils.coininfo.entities.Coin
import kotlinx.android.synthetic.main.item_coin.view.*

class CoinRecyclerViewAdapter : RecyclerView.Adapter<CoinRecyclerViewAdapter.ViewHolder>() {

    var coins: List<Coin> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickCoin: (Coin) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_coin,
                parent,
                false
            ),
            onClickCoin
        )
    }

    override fun getItemCount(): Int = coins.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(coins[position])
    }

    inner class ViewHolder(itemView: View, private val onClickCoin: (Coin) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(coin: Coin) {
            itemView.coinCard.setOnClickListener { onClickCoin(coin) }
            itemView.coinName.text = coin.name
        }
    }
}
