package com.felipeacerbi.foodradar.feature_radar.mapper

import com.felipeacerbi.foodradar.feature_radar.model.RestaurantResult
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantWithFavorite
import javax.inject.Inject

internal class RestaurantResultMapper @Inject constructor() {

    fun map(restaurantWithFavorite: RestaurantWithFavorite) =
        RestaurantResult(
            id = restaurantWithFavorite.restaurant.id,
            name = restaurantWithFavorite.restaurant.name,
            shortDescription = restaurantWithFavorite.restaurant.shortDescription,
            imageUrl = restaurantWithFavorite.restaurant.imageUrl,
            isFavorite = restaurantWithFavorite.isFavorite
        )
}