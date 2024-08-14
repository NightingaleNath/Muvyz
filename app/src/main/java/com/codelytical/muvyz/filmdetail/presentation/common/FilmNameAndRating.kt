package com.codelytical.muvyz.filmdetail.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FilmNameAndRating(
    modifier: Modifier = Modifier,
    filmName: String,
    rating: Float
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.83f),
                text = filmName,
                style = MaterialTheme.typography.titleMedium,
            )
            VoteAverageRatingIndicator(
                percentage = rating
            )
        }
    }
}
