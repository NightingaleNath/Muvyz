package com.codelytical.muvyz.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.codelytical.muvyz.core.util.Resource
import com.codelytical.muvyz.genre.domain.usecase.GetMovieGenresUseCase
import com.codelytical.muvyz.genre.domain.usecase.GetTvSeriesGenresUseCase
import com.codelytical.muvyz.search.domain.usecase.SearchFilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getMovieGenresUseCase: GetMovieGenresUseCase,
    private val getTvSeriesGenresUseCase: GetTvSeriesGenresUseCase,
    private val searchFilmUseCase: SearchFilmUseCase,
) : ViewModel() {

    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState = _searchUiState.asStateFlow()

    private var searchJob: Job? = null

    fun searchAll(searchParam: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _searchUiState.update {
                it.copy(
                    searchResult = searchFilmUseCase(searchParam).cachedIn(viewModelScope)
                )
            }
        }
    }

    fun updateSearchTerm(value: String) {
        _searchUiState.update {
            it.copy(searchTerm = value)
        }
    }

    fun getMoviesGenres() {
        viewModelScope.launch {
            _searchUiState.update {
                it.copy(
                    isLoadingGenres = true,
                )
            }
            when (val result = getMovieGenresUseCase()) {
                is Resource.Error -> {
                    _searchUiState.update {
                        it.copy(
                            isLoadingGenres = false,
                        )
                    }
                }

                is Resource.Success -> {
                    _searchUiState.update {
                        it.copy(
                            isLoadingGenres = false,
                            moviesGenres = result.data ?: emptyList()
                        )
                    }
                }

                is Resource.Loading -> {
                    searchUiState
                }
            }
        }
    }

    fun getTvSeriesGenres() {
        viewModelScope.launch {
            _searchUiState.update {
                it.copy(
                    isLoadingGenres = true,
                )
            }
            when (val result = getTvSeriesGenresUseCase()) {
                is Resource.Error -> {
                    _searchUiState.update {
                        it.copy(
                            isLoadingGenres = false,
                        )
                    }
                }

                is Resource.Success -> {
                    _searchUiState.update {
                        it.copy(
                            isLoadingGenres = false,
                            tvSeriesGenres = result.data ?: emptyList()
                        )
                    }
                }

                is Resource.Loading -> {
                    searchUiState
                }
            }
        }
    }

    fun clearSearch() {
        _searchUiState.update {
            it.copy(
                searchResult = emptyFlow(),
                searchTerm = ""
            )
        }
    }
}
