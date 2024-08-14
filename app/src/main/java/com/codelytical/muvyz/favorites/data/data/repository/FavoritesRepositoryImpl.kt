package com.codelytical.muvyz.favorites.data.data.repository

import com.codelytical.muvyz.favorites.data.data.local.Favorite
import com.codelytical.muvyz.favorites.data.data.local.FavoritesDatabase
import com.codelytical.muvyz.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(private val database: FavoritesDatabase):
    FavoritesRepository {
    override suspend fun insertFavorite(favorite: Favorite) {
        database.dao.insertFavorite(favorite)
    }

    override fun getFavorites(): Flow<List<Favorite>> {
        return database.dao.getAllFavorites()
    }

    override fun isFavorite(mediaId: Int): Flow<Boolean>{
        return database.dao.isFavorite(mediaId)
    }

    override fun getAFavorites(mediaId: Int): Flow<Favorite?> {
        return database.dao.getAFavorites(mediaId)
    }

    override suspend fun deleteOneFavorite(favorite: Favorite) {
        database.dao.deleteAFavorite(favorite)
    }

    override suspend fun deleteAllFavorites() {
        database.dao.deleteAllFavorites()
    }
}
