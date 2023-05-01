package com.gardner.showmecars.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gardner.showmecars.data.remote.SnappCarApi
import com.gardner.showmecars.data.remote.dto.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val api: SnappCarApi
) : ViewModel() {
    
    private val _cars = MutableStateFlow<List<Result>>(emptyList())
    val cars = _cars.asStateFlow()
    
    private val _offset = MutableStateFlow(0)
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage.asStateFlow()
    
    fun searchCars(city: String, country: String, limit: Int = 10) {
        viewModelScope.launch {
            try {
                val (latitude, longitude) = getLatLngForCity(city, country)
                val response =
                    api.getCars(
                        country = country,
                        latitude = latitude,
                        longitude = longitude,
                        offset = _offset.value,
                        limit = limit
                    )
                if (response.isSuccessful) {
                    val newResults = response.body()?.results ?: emptyList()
                    _cars.value += newResults
                    _offset.value = _cars.value.size
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
    
    private fun getLatLngForCity(city: String, country: String): Pair<Double, Double> {
        // Hardcoded cities and their latitude/longitude
        val cities = mapOf(
            "Amsterdam" to Pair(52.3676, 4.9041),
            "Utrecht" to Pair(52.0907, 5.1214)
        )
        
        return cities[city] ?: cities.values.first()
    }
    
    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}