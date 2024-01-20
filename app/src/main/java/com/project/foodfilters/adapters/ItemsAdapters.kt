package com.project.foodfilters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.foodfilters.R
import com.project.foodfilters.databinding.FiltersItemBinding
import com.project.foodfilters.model.Taxonomy
import com.project.foodfilters.ui.MainActivity

class ItemsAdapters(
    var viewPresenter: MainActivity,
   private var taxonomy: ArrayList<Taxonomy>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(viewPresenter)

    inner class FilterViewHolder(private var binding: FiltersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemList: Taxonomy, position: Int) {
            binding.apply {
                presenter = viewPresenter
                filterRadio.text = itemList.name
                filterRadio.setOnClickListener {
                    viewPresenter.selectedName(itemList.name,position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: FiltersItemBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.filters_item,
                parent,
                false
            )
        return FilterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taxonomy.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FilterViewHolder).bind(taxonomy[position], position)
    }
}