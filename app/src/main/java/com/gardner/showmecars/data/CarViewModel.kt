package com.gardner.showmecars.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gardner.showmecars.data.remote.SnappCarApi
import com.gardner.showmecars.data.remote.dto.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val api: SnappCarApi
) : ViewModel() {
    
    private val _cars = MutableStateFlow<List<Result>>(emptyList())
    val cars = _cars.asStateFlow()
    
    fun searchCars(city: String, country: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val (latitude, longitude) = getLatLngForCity(city, country)
                val response =
                    api.getCars(country = country, latitude = latitude, longitude = longitude)
                if (response.isSuccessful) {
                    _cars.value = response.body()?.results ?: emptyList()
                }
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
}