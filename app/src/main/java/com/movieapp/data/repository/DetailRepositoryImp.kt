package com.movieapp.data.repository

import com.movieapp.data.remote.MovieApi
import com.movieapp.data.remote.dto.MovieResponseDto
import com.movieapp.domain.repository.DetailRepository
import com.movieapp.domain.repository.HomeRepository
import javax.inject.Inject

class DetailRepositoryImp @Inject constructor(
    private val api: MovieApi
) : DetailRepository {
    override suspend fun getDetail(id: Int): MovieResponseDto {
        return api.getMovieDetail(id)
    }
}