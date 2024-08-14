package com.codelytical.muvyz.genre.data.repository

import com.codelytical.muvyz.core.data.network.MovieApi
import com.codelytical.muvyz.core.util.Resource
import com.codelytical.muvyz.genre.data.mappers.toDomain
import com.codelytical.muvyz.genre.domain.model.Genre
import com.codelytical.muvyz.genre.domain.repository.GenreRepository
import timber.log.Timber
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val api: MovieApi,
) : GenreRepository {
    override suspend fun getMovieGenres(): Resource<List<Genre>> {
        val response = try {
            api.getMovieGenres()
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Movies genres: $response")
        return Resource.Success(
            response.genres.map { it.toDomain() }
        )
    }

    override suspend fun getTvSeriesGenres(): Resource<List<Genre>> {
        val response = try {
            api.getTvSeriesGenres()
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Series genres: $response")
        return Resource.Success(
            response.genres.map { it.toDomain() }
        )
    }
}
