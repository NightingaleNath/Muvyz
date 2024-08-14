package com.codelytical.muvyz.favorites.domain.repository

import com.codelytical.muvyz.favorites.data.data.local.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun insertFavorite(favorite: Favorite)
    fun getFavorites(): Flow<List<Favorite>>
    fun isFavorite(mediaId: Int): Flow<Boolean>
    fun getAFavorites(mediaId: Int): Flow<Favorite?>
    suspend fun deleteOneFavorite(favorite: Favorite)
    suspend fun deleteAllFavorites()
}
