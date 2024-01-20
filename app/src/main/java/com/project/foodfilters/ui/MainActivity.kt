package com.project.foodfilters.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.project.foodfilters.R
import com.project.foodfilters.adapters.ChosenItems
import com.project.foodfilters.adapters.FiltersAdapter
import com.project.foodfilters.databinding.ActivityMainBinding
import com.project.foodfilters.model.Category
import com.project.foodfilters.model.RestaurantData
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var chosenAdapter: ChosenItems? = null
    private var filterAdapter: FiltersAdapter? = null
    private var selectedData = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (binding == null) {
            binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)
        }
        binding?.apply {
            lifecycleOwner = this@MainActivity
            executePendingBindings()
        }

        val data = readRTFFile()

        val gson: Gson = GsonBuilder().setLenient().create()

        val itemsResponse = gson.fromJson(data, RestaurantData::class.java)

        val indexOfSort = itemsResponse.data.indexOfFirst { it.slug == "sort" }

        if (indexOfSort != -1) {
            val elementToMove = itemsResponse.data.removeAt(indexOfSort)

            itemsResponse.data.add(0, elementToMove)
        }

        println("ModelData:- $itemsResponse")
        setRecyclerViews(itemsResponse.data)
    }

    private fun setRecyclerViews(data: List<Category>) {
        filterAdapter = FiltersAdapter(this, data as ArrayList<Category>)
        binding?.rvFilters?.adapter = filterAdapter

        chosenAdapter = ChosenItems(this)
        binding?.rvChosenItems?.adapter = chosenAdapter
    }

    private fun readRTFFile(): String {
        var content = ""

        try {
            val inputStream = this.assets.open("filter.rtf")

            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            content = String(buffer)

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return content
    }

    fun selectedName(name: String, pos: Int) {
        selectedData.add(name)
        selectedData.let { chosenAdapter?.updateList(it) }
    }

    fun showItems(pos: Int) {
        filterAdapter?.updateList(pos, 0)
    }

    fun deSelect(chosenPos: Int) {
        selectedData.removeAt(chosenPos)
        selectedData.let { chosenAdapter?.updateList(it) }
    }
}