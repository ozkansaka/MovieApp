package com.movieapp.presentation.category_detail

import com.movieapp.domain.model.Categories
import com.movieapp.domain.model.CategoryDetail

data class CategoryDetailUiState(
    val isLoading: Boolean = false,
    val categoryDetail: List<CategoryDetail> = emptyList(),
    val error: String = ""
)
