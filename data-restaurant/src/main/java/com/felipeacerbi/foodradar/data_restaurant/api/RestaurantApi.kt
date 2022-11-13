package com.felipeacerbi.foodradar.data_restaurant.api

import com.felipeacerbi.foodradar.data_restaurant.model.RestaurantResults
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RestaurantApi {

    @GET("pages/restaurants")
    suspend fun getRestaurants(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): RestaurantResults
}