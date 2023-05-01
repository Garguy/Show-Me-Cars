package com.gardner.showmecars.ui.screens.carlist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gardner.showmecars.data.remote.dto.Result

@Composable
fun CarListItem(carResult: Result) {
    val car = carResult.car
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(text = "${car.make} ${car.model}", fontSize = 20.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "€${carResult.priceInformation.price} per day", fontSize = 20.sp)
    }
}