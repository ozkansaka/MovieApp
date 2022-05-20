package com.movieapp.presentation.categories

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
import com.movieapp.domain.model.HomeTypeModel
import com.movieapp.extensions.loadImage

class CategoriesAdapter : ListAdapter<Categories, CategoriesAdapter.CategoriesViewHolder>(CategoriesDiffCallback) {


    inner class CategoriesViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: Categories) {

            binding.categoryTitle.text = content.name
            binding.categoryTitle.setOnClickListener {
                it.findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToCategoryDetailFragment(id = content.id, title = content.name))
            }
        }
    }


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoriesViewHolder(binding)
    }

}

object CategoriesDiffCallback : DiffUtil.ItemCallback<Categories>() {
    override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
        return oldItem == newItem
    }

}