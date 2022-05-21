package com.movieapp.domain.model

import com.movieapp.data.remote.dto.MovieResponseDto

data class Search(
    val id:Int,
    val title: String?,
    val image: String?,
    val imdb:String?,
    val description: String?,
)
