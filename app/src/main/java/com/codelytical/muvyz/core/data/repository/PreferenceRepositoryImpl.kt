package com.codelytical.muvyz.core.data.repository

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.codelytical.muvyz.core.domain.repository.PreferenceRepository
import com.codelytical.muvyz.core.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : PreferenceRepository {
    override suspend fun saveTheme(themeValue: Int) {
        dataStore.edit { preferences ->
            preferences[Constants.THEME_OPTIONS] = themeValue
        }
    }

    override fun getTheme(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[Constants.THEME_OPTIONS] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
    }
}
