package com.codelytical.muvyz.search.presentation

import androidx.paging.PagingData
import com.codelytical.muvyz.genre.domain.model.Genre
import com.codelytical.muvyz.search.domain.model.Search
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchUiState(
    val isLoadingGenres: Boolean = false,
    val searchTerm: String = "",
    val searchResult: Flow<PagingData<Search>> = emptyFlow(),
    val moviesGenres: List<Genre> = emptyList(),
    val tvSeriesGenres: List<Genre> = emptyList()
)
