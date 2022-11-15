package com.felipeacerbi.foodradar.feature_radar.mapper

import com.felipeacerbi.foodradar.core_favorite.Favorite
import com.felipeacerbi.foodradar.core_restaurant.Restaurant
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantWithFavorite
import javax.inject.Inject

class RestaurantWithFavoriteMapper @Inject constructor() {

    fun map(restaurant: Restaurant, favorites: List<Favorite>) =
        RestaurantWithFavorite(
            restaurant = restaurant,
            isFavorite = favorites.find { it.id == restaurant.id }?.isFavorite ?: false
        )
}