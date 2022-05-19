package com.movieapp.domain.repository

import com.movieapp.data.remote.dto.MovieResponseDto

interface DetailRepository {
    suspend fun getDetail(id:Int): MovieResponseDto
}