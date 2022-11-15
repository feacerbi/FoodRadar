package com.felipeacerbi.foodradar.feature_radar.usecase

import com.felipeacerbi.foodradar.core_location.LocationRepository
import com.felipeacerbi.foodradar.feature_radar.mapper.RestaurantResultMapper
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantResult
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantWithFavorite
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalCoroutinesApi
internal class GetNearbyRestaurantsUseCase @Inject constructor(
    private val getRestaurantsWithFavoritesUseCase: GetRestaurantsWithFavoritesUseCase,
    private val locationRepository: LocationRepository,
    private val restaurantResultMapper: RestaurantResultMapper
) {

    operator fun invoke(): Flow<List<RestaurantResult>> =
        locationRepository.getCurrentLocation()
            .flatMapLatest { location -> getRestaurantsWithFavoritesUseCase(location) }
            .map { results -> results.take(MAX_NUMBER_OF_RESTAURANT_RESULTS) }
            .map { results -> results.toRestaurantResults() }

    private fun List<RestaurantWithFavorite>.toRestaurantResults() =
        map { restaurantResultMapper.map(it) }

    companion object {
        private const val MAX_NUMBER_OF_RESTAURANT_RESULTS = 15
    }
}