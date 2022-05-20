package com.movieapp.presentation.detail

import com.movieapp.domain.model.Detail

data class DetailUiState(
    val isLoading: Boolean = false,
    val detail: List<Detail> = emptyList(),
    val error: String = ""
)
