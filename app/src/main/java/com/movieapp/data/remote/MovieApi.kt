package com.movieapp.data.remote

import com.movieapp.data.remote.dto.MovieResponseDto
import retrofit2.http.GET

interface MovieApi {

    @GET("/3/movie/popular?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getPopular(): MovieResponseDto

    @GET("/3/movie/top_rated?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getTopRated(): MovieResponseDto

    @GET("/3/movie/upcoming?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getUpcoming(): MovieResponseDto
}