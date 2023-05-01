package com.gardner.showmecars.data.remote.dto

data class Car(
    val accessories: List<String>?,
    val address: Address?,
    val allowed: List<String>?,
    val bodyType: String?,
    val carCategory: String?,
    val createdAt: String?,
    val fuelType: String?,
    val gear: String?,
    val images: List<String>?,
    val make: String?,
    val model: String?,
    val ownerId: String?,
    val reviewCount: Int?,
    val seats: Int?,
    val year: Int?
)