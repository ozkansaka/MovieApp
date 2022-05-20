package com.movieapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movieapp.R
import com.movieapp.databinding.FragmentDetailBinding
import com.movieapp.extensions.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottomBar)
        navBar.visibility = View.GONE

        WindowInsetsControllerCompat(requireActivity().window, requireActivity().window.decorView).isAppearanceLightStatusBars = false


        val binding = FragmentDetailBinding.inflate(inflater)

        viewModel.movieId = args.movieId
        viewModel.run()

        binding.backButton.setOnClickListener {
            it.findNavController().popBackStack()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when {
                    uiState.detail.isNotEmpty() -> {
                        binding.progressBar.visibility = View.GONE
                        uiState.detail[0].image?.let { binding.detailImage.loadImage("https://image.tmdb.org/t/p/w500$it") }
                        uiState.detail[0].banner?.let { binding.detailBanner.loadImage("https://image.tmdb.org/t/p/w500$it") }
                        binding.detailTitle.text = uiState.detail[0].title
                        binding.detailDateAndTime.text = uiState.detail[0].runtime + " mins"
                        binding.detailImdb.text = uiState.detail[0].imdb
                        binding.detailAbout.text = uiState.detail[0].description

                        for (i in uiState.detail[0].genres) {
                            binding.genresContainer.addView(getGenres(i.name))
                        }

                        for (i in uiState.detail[0].cast!!) {
                            binding.castContainer.addView(getCast(i.profilePath, i.name))
                        }

                    }
                    uiState.isLoading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    uiState.error.isNotEmpty() -> {}
                }
            }
        }
        return binding.root
    }


    private fun getGenres(genre: String): ConstraintLayout {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.item_genres, null) as ConstraintLayout
        val title = layout.findViewById(R.id.genre_text) as TextView
        title.text = genre
        return layout
    }

    private fun getCast(url: String, name: String): ConstraintLayout {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.item_cast, null) as ConstraintLayout
        val title = layout.findViewById(R.id.cast_name) as TextView
        title.text = name

        val image = layout.findViewById(R.id.cast_image) as ImageView

        image.loadImage("https://image.tmdb.org/t/p/w500$url")

        return layout
    }

}