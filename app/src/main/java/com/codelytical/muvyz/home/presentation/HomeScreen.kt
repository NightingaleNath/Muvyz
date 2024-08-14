
package com.codelytical.muvyz.home.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.codelytical.muvyz.R
import com.codelytical.muvyz.core.domain.model.Film
import com.codelytical.muvyz.core.util.Constants.TYPE_MOVIE
import com.codelytical.muvyz.core.util.Constants.TYPE_TV_SERIES
import com.codelytical.muvyz.core.util.createImageUrl
import com.codelytical.muvyz.home.domain.model.Movie
import com.codelytical.muvyz.home.domain.model.Series
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.FilmDetailsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalSharedTransitionApi::class)
@Destination<RootGraph>(start = true)
@Composable
fun SharedTransitionScope.HomeScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val context = LocalContext.current

    HomeScreenContent(
        state = homeUiState,
        animatedVisibilityScope = animatedVisibilityScope,
        onEvent = { event ->
            when (event) {
                is HomeUiEvents.NavigateBack -> {
                    navigator.navigateUp()
                }

                is HomeUiEvents.NavigateToFilmDetails -> {
                    navigator.navigate(
                        FilmDetailsScreenDestination(
                            film = event.film,
                        )
                    )
                }

                is HomeUiEvents.OnFilmGenreSelected -> {
                    if (homeUiState.selectedFilmOption == context.getString(R.string.movies)) {
                        viewModel.setGenre(event.genre)
                        viewModel.getTrendingMovies(event.genre.id)
                        viewModel.getTopRatedMovies(event.genre.id)
                        viewModel.getUpcomingMovies(event.genre.id)
                        viewModel.getNowPayingMovies(event.genre.id)
                        viewModel.getPopularMovies(event.genre.id)
                    } else if (homeUiState.selectedFilmOption == context.getString(R.string.tv_shows)) {
                        viewModel.setGenre(event.genre)
                        viewModel.getTrendingTvSeries(event.genre.id)
                        viewModel.getTopRatedTvSeries(event.genre.id)
                        viewModel.getAiringTodayTvSeries(event.genre.id)
                        viewModel.getOnTheAirTvSeries(event.genre.id)
                        viewModel.getPopularTvSeries(event.genre.id)
                    }
                }

                is HomeUiEvents.OnFilmOptionSelected -> {
                    viewModel.setSelectedOption(event.item)
                }

                HomeUiEvents.OnPullToRefresh -> {
                    viewModel.refreshAllData()
                }
            }
        }
    )
}



fun Movie.toFilm(
    category: String,
) = Film(
    id = id,
    type = TYPE_MOVIE,
    image = posterPath.createImageUrl(),
    category = category,
    name = title,
    rating = voteAverage.toFloat(),
    releaseDate = releaseDate,
    overview = overview
)

fun Series.toFilm(
    category: String,
) = Film(
    id = id,
    type = TYPE_TV_SERIES,
    image = posterPath.createImageUrl(),
    category = category,
    name = name,
    rating = voteAverage.toFloat(),
    releaseDate = firstAirDate,
    overview = overview
)
