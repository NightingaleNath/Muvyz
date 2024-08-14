package com.codelytical.muvyz.genre.domain.usecase

import com.codelytical.muvyz.genre.domain.repository.GenreRepository
import javax.inject.Inject

class GetMovieGenresUseCase @Inject constructor(
    private val repository: GenreRepository
) {
    suspend operator fun invoke() = repository.getMovieGenres()
}
