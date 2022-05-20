package com.movieapp.data.remote.dto

import com.movieapp.domain.model.Categories
import com.movieapp.domain.model.Detail
import com.movieapp.domain.model.Home

fun MovieResponseDto.toHome(): Home {
    return Home(
        id=id,
        imdb=voteAverage.toString(),
        title = title,
        image = backdropPath,
        poster = posterPath,
    )
}

fun MovieResponseDto.toDetail(): Detail {
    return Detail(
        title = title,
        image = posterPath,
        banner = backdropPath,
        description = overview,
        imdb = voteAverage.toString(),
        genres=genres,
        runtime=runtime,
    )
}


fun MovieResponseDto.toCategories(): Categories {
    return Categories(
        id = id,
        name = name,
    )
}
