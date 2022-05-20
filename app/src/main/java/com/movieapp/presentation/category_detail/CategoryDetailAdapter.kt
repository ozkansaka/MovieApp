package com.movieapp.presentation.category_detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.R
import com.movieapp.databinding.*
import com.movieapp.domain.model.Categories
import com.movieapp.domain.model.CategoryDetail
import com.movieapp.domain.model.HomeTypeModel
import com.movieapp.extensions.loadImage

class CategoryDetailAdapter : ListAdapter<CategoryDetail, CategoryDetailAdapter.CategoryDetailViewHolder>(CategoryDetailDiffCallback) {


    inner class CategoryDetailViewHolder(private val binding: ItemCategoryDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: CategoryDetail) {

            content.poster?.let { binding.categoryDetailImage.loadImage("https://image.tmdb.org/t/p/w500$it") }
            binding.categoryDetailTitle.text = content.title
            binding.categoryDetailImdb.text = content.imdb
            binding.constraint.setOnClickListener {
                it.findNavController().navigate(CategoryDetailFragmentDirections.actionCategoryDetailFragmentToDetailFragment(content.id))
            }
        }
    }


    override fun onBindViewHolder(holder: CategoryDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryDetailBinding.inflate(layoutInflater, parent, false)
        return CategoryDetailViewHolder(binding)
    }

}

object CategoryDetailDiffCallback : DiffUtil.ItemCallback<CategoryDetail>() {
    override fun areItemsTheSame(oldItem: CategoryDetail, newItem: CategoryDetail): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CategoryDetail, newItem: CategoryDetail): Boolean {
        return oldItem == newItem
    }

}