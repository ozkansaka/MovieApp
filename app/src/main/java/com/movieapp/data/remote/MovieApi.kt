package com.movieapp.data.remote

import com.movieapp.data.remote.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/movie/popular?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getPopular(): MovieResponseDto

    @GET("/3/movie/top_rated?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getTopRated(): MovieResponseDto

    @GET("/3/movie/upcoming?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getUpcoming(): MovieResponseDto

    @GET("/3/movie/{id}?api_key=e2c019e3bbc9049df7b03972b44ff529&append_to_response=credits")
    suspend fun getMovieDetail(@Path("id") id: Int): MovieResponseDto

    @GET("/3/genre/movie/list?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getCategories(): MovieResponseDto

    @GET("/3/discover/movie?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getCategoryDetail(@Query("with_genres") id: Int): MovieResponseDto

    @GET("/3/search/movie?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getSearch(@Query("query") query: String): MovieResponseDto

}