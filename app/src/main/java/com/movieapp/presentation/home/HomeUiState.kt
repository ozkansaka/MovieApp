package com.movieapp.presentation.home

import com.movieapp.domain.model.Home
import com.movieapp.domain.model.HomeTypeModel

data class HomeUiState(
    val isLoading: Boolean = false,
    val home: List<HomeTypeModel> = emptyList(),
    val error: String = ""
)
