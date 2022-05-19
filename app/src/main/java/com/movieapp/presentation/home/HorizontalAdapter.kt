package com.movieapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.databinding.ItemHorizontalBinding
import com.movieapp.domain.model.Home
import com.movieapp.extensions.loadImage


class HorizontalAdapter(dataList: List<Home>) : RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    private var data: List<Home> = ArrayList()

    init {
        this.data = dataList
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ViewHolder(private val binding: ItemHorizontalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Home) {
            item.poster?.let { binding.horizontalImage.loadImage("https://image.tmdb.org/t/p/w500$it") }
            binding.horizontalTitle.text = item.title
            binding.horizontalImdb.text = item.imdb
            binding.horizontalImage.setOnClickListener{
                it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(item.id))
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHorizontalBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size

}