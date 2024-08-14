package com.codelytical.muvyz.favorites.data.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.codelytical.muvyz.favorites.data.data.local.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert
    suspend fun insertFavorite(favorite: Favorite)

    @Query("SELECT * FROM fav_table ORDER BY mediaId DESC")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM fav_table WHERE mediaId  == :mediaId")
    fun getAFavorites(mediaId: Int): Flow<Favorite?>

    @Query("SELECT favorite FROM fav_table WHERE mediaId = :mediaId")
    fun isFavorite(mediaId: Int): Flow<Boolean>

    @Delete
    suspend fun deleteAFavorite(favorite: Favorite)

    @Query("DELETE FROM fav_table")
    suspend fun deleteAllFavorites()
}
