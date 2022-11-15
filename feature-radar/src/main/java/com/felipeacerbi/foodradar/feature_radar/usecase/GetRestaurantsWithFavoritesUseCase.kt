package com.felipeacerbi.foodradar.feature_radar.usecase

import com.felipeacerbi.foodradar.core_favorite.Favorite
import com.felipeacerbi.foodradar.core_favorite.FavoriteRepository
import com.felipeacerbi.foodradar.core_location.Location
import com.felipeacerbi.foodradar.core_restaurant.Restaurant
import com.felipeacerbi.foodradar.core_restaurant.RestaurantRepository
import com.felipeacerbi.foodradar.feature_radar.mapper.RestaurantWithFavoriteMapper
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantWithFavorite
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalCoroutinesApi
internal class GetRestaurantsWithFavoritesUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val restaurantRepository: RestaurantRepository,
    private val restaurantWithFavoriteMapper: RestaurantWithFavoriteMapper
) {

    operator fun invoke(location: Location): Flow<List<RestaurantWithFavorite>> = flow {
        val restaurants = restaurantRepository.getRestaurantsByLocation(location.latitude, location.longitude)
        emitAll(restaurants.withFavorites())
    }

    private fun List<Restaurant>.withFavorites() =
        favoriteRepository.getFavorites()
            .map { favorites -> this.withFavorites(favorites) }

    private fun List<Restaurant>.withFavorites(favorites: List<Favorite>) =
        map { restaurant -> restaurantWithFavoriteMapper.map(restaurant, favorites) }
}