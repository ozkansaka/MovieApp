package com.movieapp.data.repository

import com.movieapp.data.remote.MovieApi
import com.movieapp.data.remote.dto.MovieResponseDto
import com.movieapp.domain.repository.IRepository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val api: MovieApi
) : IRepository {
    override suspend fun getPopular(): MovieResponseDto {
        return api.getPopular()
    }

    override suspend fun getTopRated(): MovieResponseDto {
        return api.getTopRated()
    }

    override suspend fun getUpcoming(): MovieResponseDto {
        return api.getUpcoming()
    }

    override suspend fun getCategories(): MovieResponseDto {
        return api.getCategories()
    }

    override suspend fun getDetail(id: Int): MovieResponseDto {
        return api.getMovieDetail(id)
    }

    override suspend fun getCategoryDetail(id: Int): MovieResponseDto {
        return api.getCategoryDetail(id)
    }
}