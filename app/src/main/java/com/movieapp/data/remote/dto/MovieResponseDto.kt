package com.movieapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    val page: Int,
    val results: List<DivanModelDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
