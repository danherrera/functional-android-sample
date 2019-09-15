package com.robotsandpencils.coininfo.presentation.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robotsandpencils.coininfo.R
import kotlinx.android.synthetic.main.item_start_end_text.view.*

class StartEndTextRecyclerViewAdapter : RecyclerView.Adapter<StartEndTextRecyclerViewAdapter.ViewHolder>() {

    var viewModels: List<StartEndTextViewModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_start_end_text,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = viewModels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModels[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(viewModel: StartEndTextViewModel) {
            itemView.startText.text = viewModel.startText
            itemView.endText.text = viewModel.endText
        }
    }
}