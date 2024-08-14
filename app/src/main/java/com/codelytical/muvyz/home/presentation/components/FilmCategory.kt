package com.codelytical.muvyz.home.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.codelytical.muvyz.home.presentation.HomeUiEvents
import com.codelytical.muvyz.home.presentation.HomeUiState

@Composable
fun FilmCategory(
    items: List<String>,
    modifier: Modifier = Modifier,
    state: HomeUiState,
    onEvent: (HomeUiEvents) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        items.forEach { item ->
            val lineLength = animateFloatAsState(
                targetValue = if (item == state.selectedFilmOption) 2f else 0f,
                animationSpec = tween(
                    durationMillis = 300
                ),
                label = "lineLength"
            )

            val primaryColor = MaterialTheme.colorScheme.primary
            Text(
                text = item,
                modifier = Modifier
                    .padding(8.dp)
                    .drawBehind {
                        if (item == state.selectedFilmOption) {
                            if (lineLength.value > 0f) {
                                drawLine(
                                    color = primaryColor,
                                    start = Offset(
                                        size.width / 2f - lineLength.value * 10.dp.toPx(),
                                        size.height
                                    ),
                                    end = Offset(
                                        size.width / 2f + lineLength.value * 10.dp.toPx(),
                                        size.height
                                    ),
                                    strokeWidth = 2.dp.toPx(),
                                    cap = StrokeCap.Round
                                )
                            }
                        }
                    }
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onEvent(
                            HomeUiEvents.OnFilmOptionSelected(
                                item
                            )
                        )
                    },
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = if (item == state.selectedFilmOption) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground.copy(
                        .5f
                    ),
                )
            )
        }
    }
}