package com.gardner.showmecars.ui.screens.carlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.gardner.showmecars.R
import com.gardner.showmecars.data.remote.dto.Result

@Composable
fun CarListItem(carResult: Result) {
    val car = carResult.car
    val imageUrl = car.images?.firstOrNull()
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = "${car.make}",
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            loading = { CircularProgressIndicator() },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "An error occurred loading image"
                )
            }
        )
        
        Text(text = "${car.make} ${car.model}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = "€${carResult.priceInformation.price} per day", fontSize = 14.sp)
    }
}