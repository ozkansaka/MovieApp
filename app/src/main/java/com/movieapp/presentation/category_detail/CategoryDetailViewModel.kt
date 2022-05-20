package com.movieapp.presentation.category_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.common.Resource
import com.movieapp.domain.use_case.GetCategoriesUseCase
import com.movieapp.domain.use_case.GetCategoryDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(private val getCategoryDetailUseCase: GetCategoryDetailUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryDetailUiState())
    val uiState: StateFlow<CategoryDetailUiState> = _uiState

    var categoryId = 0


    fun run() {
        getCategoryDetailUseCase.id = categoryId
        getCategoryDetail()
    }

    private fun getCategoryDetail() {
        getCategoryDetailUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _uiState.value = CategoryDetailUiState(categoryDetail = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _uiState.value = CategoryDetailUiState(error = result.message ?: "An unexpected error occured.")
                }
                is Resource.Loading -> {
                    _uiState.value = CategoryDetailUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}