package com.codelytical.muvyz.filmdetail.presentation

import com.codelytical.muvyz.cast.domain.model.Cast
import com.codelytical.muvyz.cast.domain.model.Credits
import com.codelytical.muvyz.favorites.data.data.local.Favorite

sealed interface FilmDetailsUiEvents {
    data object NavigateBack : FilmDetailsUiEvents
    data class NavigateToCastsScreen(val credits: Credits) :
        FilmDetailsUiEvents

    data class AddToFavorites(val favorite: Favorite) :
        FilmDetailsUiEvents

    data class RemoveFromFavorites(val favorite: Favorite) :
        FilmDetailsUiEvents
    data class NavigateToCastDetails(val cast: Cast) : FilmDetailsUiEvents
}
