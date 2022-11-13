package com.felipeacerbi.foodradar.core_restaurant

interface RestaurantRepository {
    suspend fun getRestaurantsByLocation(latitude: String, longitude: String): List<Restaurant>
}