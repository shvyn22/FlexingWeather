package shvyn22.flexingweather.data.preferences

import kotlinx.coroutines.flow.Flow

interface PreferencesManager {

    val isDarkTheme: Flow<Boolean>

    suspend fun editThemePreferences(newThemeValue: Boolean)
}