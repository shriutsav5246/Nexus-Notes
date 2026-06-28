package com.utsav.nexusnotes.core.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "settings"
)

object ThemePreferences {

    private val THEME_KEY = stringPreferencesKey("theme")

    fun getTheme(
        context: Context
    ): Flow<String> {

        return context.dataStore.data.map { preferences ->

            preferences[THEME_KEY] ?: "System"

        }

    }

    suspend fun saveTheme(

        context: Context,

        theme: String

    ) {

        context.dataStore.edit { preferences ->

            preferences[THEME_KEY] = theme

        }

    }

}