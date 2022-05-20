package com.movieapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    val page: Int,
    val results: List<MovieResponseDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,

    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val id: Int,
    val name: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val genres: List<MovieResponseDto>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("profile_path")
    val profilePath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String?,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val runtime: String,

    val credits: MovieResponseDto?,

    val cast: List<MovieResponseDto>,
)
