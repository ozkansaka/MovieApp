package com.movieapp.domain.repository

import com.movieapp.data.remote.dto.MovieResponseDto
import retrofit2.http.GET

interface IRepository {
    suspend fun getDetail(id: Int): MovieResponseDto
    suspend fun getPopular(): MovieResponseDto
    suspend fun getTopRated(): MovieResponseDto
    suspend fun getUpcoming(): MovieResponseDto
    suspend fun getCategories(): MovieResponseDto
}