package com.codelytical.muvyz.filmdetail.presentation

import com.codelytical.muvyz.cast.domain.model.Credits
import com.codelytical.muvyz.home.data.network.MovieDetails
import com.codelytical.muvyz.home.data.network.TvSeriesDetails

data class FilmDetailsUiState(
    val credits: Credits? = null,
    val isLoading: Boolean = false,
    val isLoadingCasts: Boolean = false,
    val error: String? = null,
    val errorCasts: String? = null,
    val tvSeriesDetails: TvSeriesDetails? = null,
    val movieDetails: MovieDetails? = null
)
