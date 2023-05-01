package com.gardner.showmecars.data.remote.dto

data class Flags(
    val favorite: Boolean,
    val instantBookable: Boolean,
    val isKeyless: Boolean,
    val new: Boolean,
    val previouslyRented: Boolean
)