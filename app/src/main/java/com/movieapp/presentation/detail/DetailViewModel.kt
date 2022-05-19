package com.movieapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.common.Resource
import com.movieapp.domain.use_case.get_detail.GetDetailUseCase
import com.movieapp.domain.use_case.get_home.GetHomeUseCase
import com.movieapp.presentation.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val getDetailUseCase: GetDetailUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    var movieId = 0


    fun run() {
        getDetailUseCase.id = movieId
        getDetail()
    }

    private fun getDetail() {

        getDetailUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.value = DetailUiState(detail = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _uiState.value = DetailUiState(error = result.message ?: "An unexpected error occured.")
                }
                is Resource.Loading -> {
                    _uiState.value = DetailUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}