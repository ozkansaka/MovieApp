package com.movieapp.data.remote.dto

import com.movieapp.domain.model.*

fun MovieResponseDto.toHome(): Home {
    return Home(
        id = id,
        imdb = voteAverage.toString(),
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
        genres = genres,
        runtime = runtime,
        cast = credits?.cast,
    )
}


fun MovieResponseDto.toCategories(): Categories {
    return Categories(
        id = id,
        name = name,
    )
}

fun MovieResponseDto.toCategoryDetail(): CategoryDetail {
    return CategoryDetail(
        id = id,
        imdb = voteAverage.toString(),
        title = title,
        image = backdropPath,
        poster = posterPath,
    )
}

fun MovieResponseDto.toSearch(): Search {
    return Search(
        id=id,
        title = title,
        image = posterPath,
        imdb = voteAverage.toString(),
        description = overview,
    )
}