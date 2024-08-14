
package com.codelytical.muvyz.home.presentation

import com.codelytical.muvyz.core.domain.model.Film
import com.codelytical.muvyz.genre.domain.model.Genre

sealed interface HomeUiEvents {
    data object NavigateBack : HomeUiEvents
    data object OnPullToRefresh : HomeUiEvents

    data class NavigateToFilmDetails(
        val film: Film,
    ) : HomeUiEvents

    data class OnFilmGenreSelected(
        val genre: Genre,
        val filmType: String,
        val selectedFilmOption: String
    ) :
        HomeUiEvents

    data class OnFilmOptionSelected(val item: String) :
        HomeUiEvents
}
