package com.movieapp.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.R
import com.movieapp.databinding.ItemChildBinding
import com.movieapp.databinding.ItemTitleBinding
import com.movieapp.databinding.ItemVerticalBinding
import com.movieapp.domain.model.HomeTypeModel
import com.movieapp.extensions.loadImage

class HomeAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = listOf<HomeTypeModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class TitleViewHolder(private val binding: ItemTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(title: HomeTypeModel.Title) {
            binding.mainTitle.text = title.title
        }
    }


    inner class HorizontalHolder(private val binding: ItemChildBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: HomeTypeModel.Horizontal) {
            val childAdapter = HorizontalAdapter(content.data)
            binding.childRecyclerView.adapter = childAdapter
            binding.childRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    inner class VerticalHolder(private val binding: ItemVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: HomeTypeModel.Vertical) {
            binding.verticalTitle.text = content.title
            content.image?.let { binding.verticalImage.loadImage("https://image.tmdb.org/t/p/w500$it") }
            binding.verticalImage.setOnClickListener{
                it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(content.id))
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {

            R.layout.item_horizontal -> HorizontalHolder(
                ItemChildBinding.inflate(layoutInflater, parent, false)
            )

            R.layout.item_vertical -> VerticalHolder(
                ItemVerticalBinding.inflate(layoutInflater, parent, false)
            )

            else -> TitleViewHolder(
                ItemTitleBinding.inflate(layoutInflater, parent, false)
            )

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when (holder) {
            is TitleViewHolder -> holder.bind(data[position] as HomeTypeModel.Title)
            is HorizontalHolder -> holder.bind(data[position] as HomeTypeModel.Horizontal)
            is VerticalHolder -> holder.bind(data[position] as HomeTypeModel.Vertical)

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is HomeTypeModel.Title -> R.layout.item_title
            is HomeTypeModel.Horizontal -> R.layout.item_horizontal
            is HomeTypeModel.Vertical -> R.layout.item_vertical
        }
    }

    override fun getItemCount() = data.size

}