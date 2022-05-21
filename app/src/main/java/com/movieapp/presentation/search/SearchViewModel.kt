package com.movieapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.common.Resource
import com.movieapp.domain.use_case.GetHomeUseCase
import com.movieapp.domain.use_case.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchUseCase: GetSearchUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    var query = "a"


    fun run() {
        getSearchUseCase.query = query
        getSearch()
    }

    private fun getSearch() {
        getSearchUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _uiState.value = SearchUiState(search = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _uiState.value = SearchUiState(error = result.message ?: "An unexpected error occured.")
                }
                is Resource.Loading -> {
                    _uiState.value = SearchUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}