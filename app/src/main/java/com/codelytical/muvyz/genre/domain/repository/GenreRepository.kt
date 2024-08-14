package com.codelytical.muvyz.genre.domain.repository

import com.codelytical.muvyz.core.util.Resource
import com.codelytical.muvyz.genre.domain.model.Genre

interface GenreRepository {
    suspend fun getMovieGenres(): Resource<List<Genre>>
    suspend fun getTvSeriesGenres(): Resource<List<Genre>>
}
