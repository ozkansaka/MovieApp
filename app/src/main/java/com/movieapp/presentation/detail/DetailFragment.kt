package com.movieapp.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
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
        val binding = FragmentDetailBinding.inflate(inflater)
        Log.e("a", args.movieId.toString())
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
                        binding.detailDateAndTime.text = uiState.detail[0].runtime +" mins"
                        binding.detailImdb.text = uiState.detail[0].imdb
                        binding.detailAbout.text = uiState.detail[0].description

                        for (i in uiState.detail[0].genres) {
                            binding.genresContainer.addView(getGenres(i.name))
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

}