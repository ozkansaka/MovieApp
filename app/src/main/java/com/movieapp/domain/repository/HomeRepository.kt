package com.movieapp.domain.repository

import com.movieapp.data.remote.dto.MovieResponseDto

interface HomeRepository {
    suspend fun getPopular(): MovieResponseDto
    suspend fun getTopRated(): MovieResponseDto
    suspend fun getUpcoming(): MovieResponseDto
}