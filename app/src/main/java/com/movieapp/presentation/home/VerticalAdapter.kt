package com.movieapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.databinding.ItemHorizontalBinding
import com.movieapp.databinding.ItemVerticalBinding
import com.movieapp.domain.model.Home
import com.movieapp.extensions.loadImage


class VerticalAdapter(dataList: List<Home>) : RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {

    private var data: List<Home> = ArrayList()

    init {
        this.data = dataList
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ViewHolder(private val binding: ItemVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Home) {
            item.image?.let { binding.verticalImage.loadImage("https://image.tmdb.org/t/p/w500$it") }
            binding.verticalTitle.text = item.title

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemVerticalBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size

}