package com.felipeacerbi.foodradar.feature_radar.model

internal data class RestaurantResult(
    val id: String,
    val name: String,
    val shortDescription: String,
    val imageUrl: String,
    val isFavorite: Boolean
)
