package com.movieapp.presentation.search

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movieapp.R
import com.movieapp.databinding.FragmentHomeBinding
import com.movieapp.databinding.FragmentSearchBinding
import com.movieapp.presentation.home.HomeAdapter
import com.movieapp.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottomBar)
        navBar.visibility = View.VISIBLE

        binding = FragmentSearchBinding.inflate(inflater)

        adapter = SearchAdapter()
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        viewModel.run()

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when {
                    uiState.search.isNotEmpty() -> {
                        adapter.submitList(uiState.search)
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.notFound.visibility = View.GONE
                        binding.searchProgressBar.visibility = View.GONE

                    }
                    uiState.isLoading -> {
                        binding.searchProgressBar.visibility = View.VISIBLE

                    }
                    uiState.search.isEmpty() -> {
                        binding.recyclerView.visibility = View.GONE
                        binding.notFound.visibility = View.VISIBLE
                        binding.searchProgressBar.visibility = View.GONE

                    }
                    uiState.error.isNotEmpty() -> {}
                }
            }
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query == "") {
                    viewModel.query = "a"
                    viewModel.run()
                } else {
                    viewModel.query = query
                    viewModel.run()
                }
                return true
            }
        })



        return binding.root
    }
}