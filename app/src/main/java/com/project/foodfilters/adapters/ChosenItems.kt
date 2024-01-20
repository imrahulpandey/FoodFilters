package com.project.foodfilters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.foodfilters.R
import com.project.foodfilters.databinding.ChosenItemsBinding
import com.project.foodfilters.ui.MainActivity

class ChosenItems(
    var presenterActivity: MainActivity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var selectedData: List<String> = emptyList()

    private val inflater: LayoutInflater = LayoutInflater.from(presenterActivity)

    inner class ChosenViewHolder(private var binding: ChosenItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String, position: Int) {
            binding.apply {
                presenter = presenterActivity
                chosenName = name
                chosenPos = position
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ChosenItemsBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.chosen_items,
                parent,
                false
            )
        return ChosenViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return selectedData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ChosenViewHolder).bind(selectedData[position], position)
    }

    fun updateList(selected: ArrayList<String>) {
        selectedData = selected
        notifyDataSetChanged()
    }

}