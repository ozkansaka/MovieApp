package com.movieapp.presentation.categories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movieapp.R
import com.movieapp.databinding.FragmentCategoriesBinding
import com.movieapp.presentation.home.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.flow.collect


@AndroidEntryPoint

class CategoriesFragment : Fragment() {


    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottomBar)
        navBar.visibility = View.VISIBLE

        val binding = FragmentCategoriesBinding.inflate(inflater)



        val adapter = CategoriesAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when {
                    uiState.categories.isNotEmpty() -> {
                        adapter.submitList(uiState.categories)
                        binding.categoriesProgressBar.visibility = View.GONE

                    }
                    uiState.isLoading -> {
                        binding.categoriesProgressBar.visibility = View.VISIBLE

                    }
                    uiState.error.isNotEmpty() -> {}
                }
            }
        }

        return binding.root
    }


}