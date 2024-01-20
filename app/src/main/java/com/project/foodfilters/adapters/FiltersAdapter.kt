package com.project.foodfilters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.foodfilters.R
import com.project.foodfilters.databinding.FiltersBinding
import com.project.foodfilters.model.Category
import com.project.foodfilters.ui.MainActivity

class FiltersAdapter(
    var presenterActivity: MainActivity,
    private var root: ArrayList<Category>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var count = 0
    private val inflater: LayoutInflater = LayoutInflater.from(presenterActivity)
    var adapter: ItemsAdapters? = null

    inner class FilterViewHolder(private var binding: FiltersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(datumList: Category, position: Int) {
            binding.apply {
                presenter = presenterActivity
                pos = position
                tvName.text = datumList.name
                tvCount.visibility = View.GONE
                if (datumList.slug == "sort") {
                    imgArrow.visibility = View.GONE
                    rvItem.visibility = View.VISIBLE
                } else {
                    imgArrow.visibility = View.VISIBLE
                    binding.rvItem.visibility = View.GONE
                }

                adapter =
                    ItemsAdapters(presenterActivity, datumList.taxonomies)
                filterItemAdapter = adapter
            }
        }

        fun updateSpecificView() {
            binding.apply {
                if (count == 0){
                    tvCount.visibility = View.GONE
                }else{
                    tvCount.visibility = View.VISIBLE
                    tvCount.text = "($count)"
                }

                if (binding.rvItem.isVisible) {
                    binding.rvItem.visibility = View.GONE
                    imgArrow.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
                } else {
                    imgArrow.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)

                    binding.rvItem.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: FiltersBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.filters,
                parent,
                false
            )
        return FilterViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return root.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>
    ) {
        if (payloads.isNotEmpty()) {
            (holder as FilterViewHolder).updateSpecificView()
        } else {
            (holder as FilterViewHolder).bind(root[position], position)
        }
    }

    fun updateList(pos: Int, itemsCount: Int) {
        count = itemsCount
        notifyItemChanged(pos, "update")
    }
}