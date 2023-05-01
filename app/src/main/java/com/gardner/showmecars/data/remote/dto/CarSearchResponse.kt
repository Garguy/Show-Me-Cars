package com.gardner.showmecars.data.remote.dto

data class CarSearchResponse(
    val results: List<Result>,
    val searchId: String,
    val sums: Sums
)