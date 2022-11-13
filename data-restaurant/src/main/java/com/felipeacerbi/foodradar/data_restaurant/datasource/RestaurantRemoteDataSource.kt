package com.felipeacerbi.foodradar.data_restaurant.datasource

import com.felipeacerbi.foodradar.data_restaurant.api.RestaurantApi
import com.felipeacerbi.foodradar.data_restaurant.model.RestaurantResults
import javax.inject.Inject

internal class RestaurantRemoteDataSource @Inject constructor(
    private val restaurantApi: RestaurantApi
) {

    suspend fun fetchRestaurants(latitude: Double, longitude: Double): RestaurantResults =
        restaurantApi.getRestaurants(latitude, longitude)
}