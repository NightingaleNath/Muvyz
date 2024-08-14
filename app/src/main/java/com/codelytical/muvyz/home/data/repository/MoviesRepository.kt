
package com.codelytical.muvyz.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codelytical.muvyz.core.data.network.MovieApi
import com.codelytical.muvyz.core.util.Constants.PAGING_SIZE
import com.codelytical.muvyz.home.domain.model.Movie
import com.codelytical.muvyz.home.data.paging.NowPlayingMoviesSource
import com.codelytical.muvyz.home.data.paging.PopularMoviesSource
import com.codelytical.muvyz.home.data.paging.TopRatedMoviesSource
import com.codelytical.muvyz.home.data.paging.TrendingMoviesSource
import com.codelytical.muvyz.home.data.paging.UpcomingMoviesSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: MovieApi) {

    fun getTrendingMoviesThisWeek(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGING_SIZE),
            pagingSourceFactory = {
                TrendingMoviesSource(api)
            }
        ).flow
    }

    fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGING_SIZE),
            pagingSourceFactory = {
                UpcomingMoviesSource(api)
            }
        ).flow
    }

    fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGING_SIZE),
            pagingSourceFactory = {
                TopRatedMoviesSource(api)
            }
        ).flow
    }

    fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGING_SIZE),
            pagingSourceFactory = {
                NowPlayingMoviesSource(api)
            }
        ).flow
    }

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGING_SIZE),
            pagingSourceFactory = {
                PopularMoviesSource(api)
            }
        ).flow
    }
}
