package com.gardner.showmecars.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gardner.showmecars.data.CarViewModel
import com.gardner.showmecars.ui.screens.carlist.CarListItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarListScreen(
    viewModel: CarViewModel = hiltViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val cars by viewModel.cars.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    
    //pagination
    val listState = rememberLazyGridState()
    val itemCountPerPage = 10
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .distinctUntilChanged()
            .filter { it != null && it >= cars.size - 2 }
            .collect {
                viewModel.searchCars(searchQuery, "NL", limit = itemCountPerPage)
            }
    }
    
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery, onValueChange = { newQuery ->
                searchQuery = newQuery
                
            },
            label = { Text(text = "Search city") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
        
        LaunchedEffect(searchQuery) {
            delay(500)
            if (searchQuery.isNotEmpty()) {
                viewModel.searchCars(searchQuery, "NL")
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(cars) { car ->
                CarListItem(carResult = car)
            }
        }
        
        if (errorMessage != null) {
            Text(
                "Error occurred: $errorMessage",
                Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
            viewModel.clearErrorMessage()
        }
    }
}