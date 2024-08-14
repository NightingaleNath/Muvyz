package com.codelytical.muvyz.favorites.data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codelytical.muvyz.favorites.data.data.local.Favorite
import com.codelytical.muvyz.favorites.data.data.local.FavoritesDao

@Database(entities = [Favorite::class], version = 1, exportSchema = true)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract val dao: FavoritesDao
}
