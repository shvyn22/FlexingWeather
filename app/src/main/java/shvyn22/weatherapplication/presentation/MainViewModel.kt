package shvyn22.weatherapplication.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import shvyn22.weatherapplication.data.local.model.Location
import shvyn22.weatherapplication.data.local.model.Weather
import shvyn22.weatherapplication.data.preferences.PreferencesManager
import shvyn22.weatherapplication.repository.Repository
import shvyn22.weatherapplication.util.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val preferences: PreferencesManager
) : ViewModel() {

    val prefs = preferences.preferencesFlow

    val items = mutableStateOf<List<Location>>(listOf())
    val currWeather = mutableStateOf<Resource<Weather>>(Resource.Undefined())

    val searchValue = mutableStateOf("")

    init {
        searchItems("")
    }

    fun searchItems(newQuery: String) = viewModelScope.launch {
        if (newQuery.isEmpty()) {
            repository.getItems().collect {
                items.value = it
            }
        } else {
            items.value = repository.searchByName(newQuery)
        }
    }

    fun getWeather(id: Int) = viewModelScope.launch {
        repository.getWeather(id).collect {
            currWeather.value = it
        }
    }

    fun resetWeather() = viewModelScope.launch {
        currWeather.value = Resource.Undefined()
    }

    fun isFavorite(id: Int) = flow {
        repository.isFavorite(id).collect {
            emit(it)
        }
    }

    fun onInsertItem(item: Weather) = viewModelScope.launch {
        repository.insert(item)
    }

    fun onDeleteItem(id: Int) = viewModelScope.launch {
        repository.delete(id)
    }

    fun onToggleMode(newValue: Boolean) = viewModelScope.launch {
        preferences.editNightMode(newValue)
    }
}