package com.felipeacerbi.foodradar.data_restaurant.repository

import com.felipeacerbi.foodradar.core_restaurant.Restaurant
import com.felipeacerbi.foodradar.core_restaurant.RestaurantRepository
import com.felipeacerbi.foodradar.data_restaurant.datasource.RestaurantRemoteDataSource
import com.felipeacerbi.foodradar.data_restaurant.di.IoDispatcher
import com.felipeacerbi.foodradar.data_restaurant.mapper.RestaurantMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class GeolocationRestaurantRepository @Inject constructor(
    private val restaurantRemoteDataSource: RestaurantRemoteDataSource,
    private val restaurantMapper: RestaurantMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RestaurantRepository {

    override suspend fun getRestaurantsByLocation(
        latitude: String,
        longitude: String
    ): List<Restaurant> = withContext(ioDispatcher) {
        restaurantRemoteDataSource.fetchRestaurants(latitude.toDouble(), longitude.toDouble())
            .let(restaurantMapper::map)
    }
}