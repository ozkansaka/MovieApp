package com.movieapp.presentation.category_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movieapp.R
import com.movieapp.databinding.FragmentCategoryDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class CategoryDetailFragment : Fragment() {


    private val viewModel: CategoryDetailViewModel by viewModels()
    private val args: CategoryDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottomBar)
        navBar.visibility = View.GONE

        val binding = FragmentCategoryDetailBinding.inflate(inflater)

        viewModel.categoryId = args.id
        binding.categoryDetailTitle.text = args.title
        viewModel.run()

        binding.categoryDetailBackButton.setOnClickListener {
            it.findNavController().popBackStack()
        }

        val adapter = CategoryDetailAdapter()
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = GridLayoutManager(context, 2)

        recyclerView.adapter = adapter


        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when {
                    uiState.categoryDetail.isNotEmpty() -> {
                        adapter.submitList(uiState.categoryDetail)
                        binding.categoryDetailProgressBar.visibility = View.GONE

                    }
                    uiState.isLoading -> {
                        binding.categoryDetailProgressBar.visibility = View.VISIBLE

                    }
                    uiState.error.isNotEmpty() -> {}
                }
            }
        }

        return binding.root
    }


}