package com.gardner.showmecars.data.remote.dto

data class PriceInformation(
    val freeKilometersPerDay: Int,
    val isoCurrencyCode: String,
    val price: Double,
    val pricePerKilometer: Double,
    val rentalDays: Int
)