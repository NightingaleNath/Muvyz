package com.codelytical.muvyz.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import retrofit2.HttpException
import java.io.IOException

@Composable
fun <T : Any> PagedRow(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<T>,
    content: @Composable (T) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.itemCount) {
                val item = items[it]
                if (item != null) {
                    content(item)
                }
            }

            items.loadState.let { loadState ->
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .align(Alignment.Center),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                CircularProgressIndicator(
                                    strokeWidth = 2.dp,
                                )
                            }
                        }
                    }

                    loadState.refresh is LoadState.NotLoading && items.itemCount < 1 -> {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .align(Alignment.Center),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "No data available",
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }


                    loadState.refresh is LoadState.Error -> {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .align(Alignment.Center),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = when ((loadState.refresh as LoadState.Error).error) {
                                        is HttpException -> {
                                            "Oops, something went wrong!"
                                        }

                                        is IOException -> {
                                            "Couldn't reach server, check your internet connection!"
                                        }

                                        else -> {
                                            "Unknown error occurred"
                                        }
                                    },
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            }
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .align(Alignment.Center),
                                    strokeWidth = 2.dp,
                                )
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "An error occurred",
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}