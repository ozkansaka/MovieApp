package com.movieapp.domain.model

sealed class HomeTypeModel {

    data class Title(
        val title: String?
    ) : HomeTypeModel()

    data class Horizontal(
        val data: List<Home>
    ) : HomeTypeModel()

    data class Vertical(
        val id: Int,
        val title: String?,
        val image: String?,
        val imdb:String?
    ) : HomeTypeModel()
}
