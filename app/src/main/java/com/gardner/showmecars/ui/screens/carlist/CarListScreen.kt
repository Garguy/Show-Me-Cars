package com.gardner.showmecars.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gardner.showmecars.data.CarViewModel
import com.gardner.showmecars.ui.screens.carlist.CarListItem
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarListScreen(
    viewModel: CarViewModel = hiltViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val cars by viewModel.cars.collectAsState()
    
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery, onValueChange = { newQuery ->
                searchQuery = newQuery
                
            },
            label = { Text(text = "Search city") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    
        LaunchedEffect(searchQuery) {
            delay(500)
            if (searchQuery.isNotEmpty()) {
                viewModel.searchCars(searchQuery, "NL")
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(cars) { car ->
                CarListItem(carResult = car)
            }
        }
        
    }
}