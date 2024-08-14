package com.codelytical.muvyz.filmdetail.data.repository

import com.codelytical.muvyz.core.data.network.MovieApi
import com.codelytical.muvyz.core.util.Resource
import com.codelytical.muvyz.home.data.network.MovieDetails
import com.codelytical.muvyz.home.data.network.TvSeriesDetails
import timber.log.Timber
import javax.inject.Inject

class FilmsDetailsRepository @Inject constructor(private val api: MovieApi) {

    // Movie Details
    suspend fun getMoviesDetails(movieId: Int): Resource<MovieDetails> {
        val response = try {
            api.getMovieDetails(movieId)
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Movie details: $response")
        return Resource.Success(response)
    }

    // Series Details
    suspend fun getTvSeriesDetails(tvId: Int): Resource<TvSeriesDetails> {
        val response = try {
            api.getTvSeriesDetails(tvId)
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Series details: $response")
        return Resource.Success(response)
    }
}
