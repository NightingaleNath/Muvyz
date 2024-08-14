package com.codelytical.muvyz.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codelytical.muvyz.home.presentation.HomeUiEvents
import com.codelytical.muvyz.home.presentation.HomeUiState

@Composable
fun Genres(
    state: HomeUiState,
    onEvent: (HomeUiEvents) -> Unit,
) {
    val genres = if (state.selectedFilmOption == "Tv Shows") {
        state.tvSeriesGenres
    } else {
        state.moviesGenres
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = genres) { genre ->
            val isSelected = genre.name == state.selectedGenre?.name

            Box(
                modifier = Modifier
                    .background(
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.background
                        },
                        shape = MaterialTheme.shapes.medium
                    )
                    .border(
                        width = .3.dp,
                        shape = MaterialTheme.shapes.medium,
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.background
                        } else {
                            MaterialTheme.colorScheme.onBackground.copy(.5f)
                        }
                    )
                    .clickable {
                        onEvent(
                            HomeUiEvents.OnFilmGenreSelected(
                                genre = genre,
                                filmType = state.selectedFilmOption,
                                selectedFilmOption = state.selectedFilmOption
                            )
                        )
                    }
            ) {
                Text(
                    text = genre.name,
                    modifier = Modifier
                        .padding(
                            horizontal = 8.dp,
                            vertical = 5.dp
                        ),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.onBackground
                        }
                    )
                )
            }
        }
    }
}
