package shvyn22.flexingweather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import shvyn22.flexingweather.data.local.model.Location
import shvyn22.flexingweather.data.local.model.Weather
import shvyn22.flexingweather.data.preferences.PreferencesManager
import shvyn22.flexingweather.repository.Repository
import shvyn22.flexingweather.util.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val preferences: PreferencesManager
) : ViewModel() {

    init {
        searchItems("")
    }

    val isDarkTheme = preferences.isDarkTheme
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    private val _locations = MutableStateFlow<Resource<List<Location>>>(Resource.Idle())
    val locations get() = _locations.asStateFlow()

    private val _weather = MutableStateFlow<Resource<Weather>>(Resource.Loading())
    val weather get() = _weather.asStateFlow()

    fun searchItems(query: String) {
        viewModelScope.launch {
            if (query.isEmpty()) {
                repository.getFavoriteLocations().collect {
                    _locations.value = it
                }
            } else {
                repository.searchLocationsByName(query).collect {
                    _locations.value = it
                }
            }
        }
    }

    fun getWeather(id: Int) {
        viewModelScope.launch {
            repository.getWeather(id).collect {
                _weather.value = it
            }
        }
    }

    fun isLocationFavorite(id: Int) = flow {
        repository.isLocationFavorite(id).collect {
            emit(it)
        }
    }

    fun insertFavoriteLocation(item: Weather) {
        viewModelScope.launch {
            repository.insertFavoriteLocation(item)
        }
    }

    fun deleteFavoriteLocation(id: Int) {
        viewModelScope.launch {
            repository.deleteFavoriteLocation(id)
        }
    }

    fun editThemePreferences(newThemeValue: Boolean) {
        viewModelScope.launch {
            preferences.editThemePreferences(newThemeValue)
        }
    }
}