package com.movieapp.domain.repository

import com.movieapp.data.remote.dto.MovieResponseDto


interface IRepository {
    suspend fun getPopular(): MovieResponseDto
    suspend fun getTopRated(): MovieResponseDto
    suspend fun getUpcoming(): MovieResponseDto
    suspend fun getDetail(id: Int): MovieResponseDto
    suspend fun getCategories(): MovieResponseDto
    suspend fun getCategoryDetail(id: Int): MovieResponseDto
    suspend fun getSearch(query: String): MovieResponseDto

}