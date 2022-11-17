package com.felipeacerbi.foodradar.feature_radar.model

import com.felipeacerbi.foodradar.core_restaurant.Restaurant

internal data class RestaurantWithFavorite(
    val restaurant: Restaurant,
    val isFavorite: Boolean
)