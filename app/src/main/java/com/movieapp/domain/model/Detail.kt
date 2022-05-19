package com.movieapp.domain.model

import com.movieapp.data.remote.dto.MovieResponseDto

data class Detail(
    val title: String?,
    val image: String?,
    val banner: String?,
    val description: String?,
    val imdb: String?,
    val genres: List<MovieResponseDto>,
    val runtime:String?
)
