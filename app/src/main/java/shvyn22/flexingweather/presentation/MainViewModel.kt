package shvyn22.flexingweather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
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

    val nightMode = preferences.nightMode

    private val _locations = MutableStateFlow<Resource<List<Location>>>(Resource.Idle())
    val locations get() = _locations.asStateFlow()

    private val _weather = MutableStateFlow<Resource<Weather>>(Resource.Loading())
    val weather get() = _weather.asStateFlow()

    fun searchItems(newQuery: String) {
        viewModelScope.launch {
            if (newQuery.isEmpty()) {
                repository.getItems().collect {
                    _locations.value = it
                }
            } else {
                repository.searchByName(newQuery).collect {
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

    fun isFavorite(id: Int) = flow {
        repository.isFavorite(id).collect {
            emit(it)
        }
    }

    fun onInsertItem(item: Weather) {
        viewModelScope.launch {
            repository.insert(item)
        }
    }

    fun onDeleteItem(id: Int) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    fun onToggleMode(newValue: Boolean) {
        viewModelScope.launch {
            preferences.editNightMode(newValue)
        }
    }
}