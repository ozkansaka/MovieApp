package com.movieapp.data.repository

import com.movieapp.data.remote.MovieApi
import com.movieapp.data.remote.dto.MovieResponseDto
import com.movieapp.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val api: MovieApi
) : HomeRepository {

    override suspend fun getPopular(): MovieResponseDto {
        return api.getPopular()
    }

    override suspend fun getTopRated(): MovieResponseDto {
        return api.getTopRated()
    }

    override suspend fun getUpcoming(): MovieResponseDto {
        return api.getUpcoming()
    }


}