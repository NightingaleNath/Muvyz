package com.codelytical.muvyz.genre.di

import com.codelytical.muvyz.genre.data.repository.GenreRepositoryImpl
import com.codelytical.muvyz.genre.domain.repository.GenreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GenreModule {
    @Binds
    abstract fun bindGenreRepository(
        genreRepositoryImpl: GenreRepositoryImpl
    ): GenreRepository
}
