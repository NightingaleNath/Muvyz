package com.codelytical.muvyz.filmdetail.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.codelytical.muvyz.R
import com.codelytical.muvyz.core.domain.model.Film
import com.codelytical.muvyz.core.presentation.components.CircleButton
import com.codelytical.muvyz.core.util.createImageUrl
import com.codelytical.muvyz.favorites.data.data.local.Favorite
import com.codelytical.muvyz.filmdetail.presentation.FilmDetailsUiEvents

@Composable
fun FilmActions(
    modifier: Modifier = Modifier,
    onEvents: (FilmDetailsUiEvents) -> Unit,
    isLiked: Boolean,
    film: Film,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CircleButton(
            onClick = {
                onEvents(FilmDetailsUiEvents.NavigateBack)
            },
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_left),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = null
            )
        }

        CircleButton(
            onClick = {
                if (isLiked) {
                    onEvents(
                        FilmDetailsUiEvents.RemoveFromFavorites(
                            Favorite(
                                favorite = false,
                                mediaId = film.id,
                                mediaType = film.type,
                                image = film.image.createImageUrl(),
                                title = film.name,
                                releaseDate = film.releaseDate,
                                rating = film.rating,
                                overview = film.overview,
                            )
                        )
                    )
                } else {
                    onEvents(
                        FilmDetailsUiEvents.AddToFavorites(
                            Favorite(
                                favorite = true,
                                mediaId = film.id,
                                mediaType = film.type,
                                image = film.image.createImageUrl(),
                                title = film.name,
                                releaseDate = film.releaseDate,
                                rating = film.rating,
                                overview = film.overview,
                            )
                        )
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                tint = if (isLiked) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.background
                },
                contentDescription = if (isLiked) {
                    stringResource(id = R.string.unlike)
                } else {
                    stringResource(id = R.string.like)
                }
            )
        }
    }
}
