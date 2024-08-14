package com.codelytical.muvyz.favorites.di

import com.codelytical.muvyz.favorites.data.data.repository.FavoritesRepositoryImpl
import com.codelytical.muvyz.favorites.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoritesModule {
    @Binds
    abstract fun bindFavoritesRepository(
        favoritesRepositoryImpl: FavoritesRepositoryImpl
    ): FavoritesRepository
}
