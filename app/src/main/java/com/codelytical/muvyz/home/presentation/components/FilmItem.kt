package com.codelytical.muvyz.home.presentation.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codelytical.muvyz.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FilmItem(
    modifier: Modifier = Modifier,
    sharedTransitionKey: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    imageUrl: String,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_placeholder),
        error = painterResource(id = R.drawable.ic_placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .sharedElement(
                state = rememberSharedContentState(
                    key = sharedTransitionKey
                ),
                animatedVisibilityScope = animatedVisibilityScope,
            )
            .fillMaxSize()
            .clip(shape = MaterialTheme.shapes.medium)
    )
}
