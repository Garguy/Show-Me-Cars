package com.gardner.showmecars.data.remote

import com.gardner.showmecars.data.remote.dto.CarSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SnappCarApi {
    
    @GET("search/query")
    suspend fun getCars(
        @Query("sort") sort: String = "price",
        @Query("country") country: String,
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("max-distance") maxDistance: Int = 3000,
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): Response<CarSearchResponse>
}