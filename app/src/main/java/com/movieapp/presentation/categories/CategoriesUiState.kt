package com.movieapp.presentation.categories

import com.movieapp.domain.model.Categories

data class CategoriesUiState(
    val isLoading: Boolean = false,
    val categories: List<Categories> = emptyList(),
    val error: String = ""
)
