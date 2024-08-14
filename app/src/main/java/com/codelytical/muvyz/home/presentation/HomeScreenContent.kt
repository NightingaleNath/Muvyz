package com.codelytical.muvyz.home.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.codelytical.muvyz.R
import com.codelytical.muvyz.core.util.createImageUrl
import com.codelytical.muvyz.home.presentation.components.AppBar
import com.codelytical.muvyz.home.presentation.components.FilmCategory
import com.codelytical.muvyz.home.presentation.components.FilmItem
import com.codelytical.muvyz.home.presentation.components.Genres
import com.codelytical.muvyz.home.presentation.components.PagedRow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreenContent(
    state: HomeUiState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onEvent: (HomeUiEvents) -> Unit,
) {
    val context = LocalContext.current
    val trendingMovies = state.trendingMovies.collectAsLazyPagingItems()
    val upcomingMovies = state.upcomingMovies.collectAsLazyPagingItems()
    val topRatedMovies = state.topRatedMovies.collectAsLazyPagingItems()
    val nowPlayingMovies = state.nowPlayingMovies.collectAsLazyPagingItems()
    val popularMovies = state.popularMovies.collectAsLazyPagingItems()
    val trendingTvSeries = state.trendingTvSeries.collectAsLazyPagingItems()
    val onAirTvSeries = state.onAirTvSeries.collectAsLazyPagingItems()
    val topRatedTvSeries = state.topRatedTvSeries.collectAsLazyPagingItems()
    val airingTodayTvSeries = state.airingTodayTvSeries.collectAsLazyPagingItems()
    val popularTvSeries = state.popularTvSeries.collectAsLazyPagingItems()

    Scaffold(
        topBar = { AppBar(onBackArrowClicked = { onEvent(HomeUiEvents.NavigateBack) }) }
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            isRefreshing = false,
            onRefresh = {
                onEvent(HomeUiEvents.OnPullToRefresh)
            }
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    FilmCategory(
                        items = listOf(
                            stringResource(R.string.movies),
                            stringResource(R.string.tv_shows)
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        state = state,
                        onEvent = onEvent,
                    )
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.genres),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Genres(
                            state = state,
                            onEvent = onEvent,
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.trending_today),
                            style = MaterialTheme.typography.titleMedium,
                        )

                        if (state.selectedFilmOption == stringResource(R.string.tv_shows)) {
                            PagedRow(
                                items = trendingTvSeries,
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    FilmItem(
                                        modifier = Modifier
                                            .height(220.dp)
                                            .width(250.dp)
                                            .clickable {
                                                onEvent(
                                                    HomeUiEvents.NavigateToFilmDetails(
                                                        film = it.toFilm(
                                                            category = context.getString(R.string.trending_today)
                                                        )
                                                    )
                                                )
                                            },
                                        imageUrl = it.posterPath.createImageUrl(),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        sharedTransitionKey = "${it.id}_${context.getString(R.string.trending_today)}",
                                    )
                                }
                            )
                        } else {
                            PagedRow(
                                items = trendingMovies,
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    FilmItem(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .width(230.dp)
                                            .clickable {
                                                onEvent(
                                                    HomeUiEvents.NavigateToFilmDetails(
                                                        film = it.toFilm(
                                                            category = context.getString(R.string.trending_today)
                                                        )
                                                    )
                                                )
                                            },
                                        imageUrl = it.posterPath.createImageUrl(),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        sharedTransitionKey = "${it.id}_${context.getString(R.string.trending_today)}",
                                    )
                                }
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = stringResource(R.string.popular),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        if (state.selectedFilmOption == stringResource(R.string.tv_shows)) {
                            PagedRow(
                                items = popularTvSeries,
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    FilmItem(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .width(130.dp)
                                            .clickable {
                                                onEvent(
                                                    HomeUiEvents.NavigateToFilmDetails(
                                                        film = it.toFilm(
                                                            category = context.getString(R.string.popular)
                                                        )
                                                    )
                                                )
                                            },
                                        imageUrl = it.posterPath.createImageUrl(),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        sharedTransitionKey = "${it.id}_${context.getString(R.string.popular)}",
                                    )
                                }
                            )
                        } else {
                            PagedRow(
                                items = popularMovies,
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    FilmItem(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .width(130.dp)
                                            .clickable {
                                                onEvent(
                                                    HomeUiEvents.NavigateToFilmDetails(
                                                        film = it.toFilm(
                                                            category = context.getString(R.string.popular)
                                                        )
                                                    )
                                                )
                                            },
                                        imageUrl = it.posterPath.createImageUrl(),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        sharedTransitionKey = "${it.id}_${context.getString(R.string.popular)}",
                                    )
                                }
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = if (state.selectedFilmOption == stringResource(R.string.tv_shows)) {
                                stringResource(R.string.on_air)
                            } else {
                                stringResource(R.string.upcoming)
                            },
                            style = MaterialTheme.typography.titleMedium,
                        )
                        if (state.selectedFilmOption == stringResource(R.string.tv_shows)) {
                            PagedRow(
                                items = onAirTvSeries,
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    FilmItem(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .width(130.dp)
                                            .clickable {
                                                onEvent(
                                                    HomeUiEvents.NavigateToFilmDetails(
                                                        film = it.toFilm(
                                                            category = context.getString(R.string.on_air)
                                                        )
                                                    )
                                                )
                                            },
                                        imageUrl = it.posterPath.createImageUrl(),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        sharedTransitionKey = "${it.id}_${context.getString(R.string.on_air)}",
                                    )
                                }
                            )
                        } else {
                            PagedRow(
                                items = upcomingMovies,
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    FilmItem(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .width(130.dp)
                                            .clickable {
                                                onEvent(
                                                    HomeUiEvents.NavigateToFilmDetails(
                                                        film = it.toFilm(
                                                            category = context.getString(R.string.upcoming)
                                                        )
                                                    )
                                                )
                                            },
                                        imageUrl = it.posterPath.createImageUrl(),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        sharedTransitionKey = "${it.id}_${context.getString(R.string.upcoming)}",
                                    )
                                }
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = if (state.selectedFilmOption == stringResource(R.string.tv_shows)) {
                                stringResource(R.string.airing_today)
                            } else {
                                stringResource(R.string.now_playing)
                            },
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                    if (state.selectedFilmOption == stringResource(R.string.tv_shows)) {
                        PagedRow(
                            items = airingTodayTvSeries,
                            modifier = Modifier.fillMaxWidth(),
                            content = {
                                FilmItem(
                                    modifier = Modifier
                                        .height(200.dp)
                                        .width(130.dp)
                                        .clickable {
                                            onEvent(
                                                HomeUiEvents.NavigateToFilmDetails(
                                                    film = it.toFilm(
                                                        category = context.getString(R.string.airing_today)
                                                    )
                                                )
                                            )
                                        },
                                    imageUrl = it.posterPath.createImageUrl(),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    sharedTransitionKey = "${it.id}_${context.getString(R.string.airing_today)}",
                                )
                            }
                        )
                    } else {
                        PagedRow(
                            items = nowPlayingMovies,
                            modifier = Modifier.fillMaxWidth(),
                            content = {
                                FilmItem(
                                    modifier = Modifier
                                        .height(200.dp)
                                        .width(130.dp)
                                        .clickable {
                                            onEvent(
                                                HomeUiEvents.NavigateToFilmDetails(
                                                    film = it.toFilm(
                                                        category = context.getString(R.string.now_playing)
                                                    )
                                                )
                                            )
                                        },
                                    imageUrl = it.posterPath.createImageUrl(),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    sharedTransitionKey = "${it.id}_${context.getString(R.string.now_playing)}",
                                )
                            }
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = stringResource(R.string.top_rated),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        if (state.selectedFilmOption == stringResource(R.string.tv_shows)) {
                            PagedRow(
                                items = topRatedTvSeries,
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    FilmItem(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .width(130.dp)
                                            .clickable {
                                                onEvent(
                                                    HomeUiEvents.NavigateToFilmDetails(
                                                        film = it.toFilm(
                                                            category = context.getString(R.string.top_rated)
                                                        )
                                                    )
                                                )
                                            },
                                        imageUrl = it.posterPath.createImageUrl(),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        sharedTransitionKey = "${it.id}_${context.getString(R.string.top_rated)}",
                                    )
                                }
                            )
                        } else {
                            PagedRow(
                                items = topRatedMovies,
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    FilmItem(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .width(130.dp)
                                            .clickable {
                                                onEvent(
                                                    HomeUiEvents.NavigateToFilmDetails(
                                                        film = it.toFilm(
                                                            category = context.getString(R.string.top_rated)
                                                        )
                                                    )
                                                )
                                            },
                                        imageUrl = it.posterPath.createImageUrl(),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        sharedTransitionKey = "${it.id}_${context.getString(R.string.top_rated)}",
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}