package com.movieapp.presentation.detail

import com.movieapp.domain.model.Detail
import com.movieapp.domain.model.Home
import com.movieapp.domain.model.HomeTypeModel

data class DetailUiState(
    val isLoading: Boolean = false,
    val detail: List<Detail> = emptyList(),
    val error: String = ""
)
