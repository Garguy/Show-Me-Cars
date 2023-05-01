package com.gardner.showmecars.data.remote.dto

data class Result(
    val car: Car,
    val ci: String,
    val flags: Flags,
    val priceInformation: PriceInformation,
    val user: User,
    val distance: Double
)