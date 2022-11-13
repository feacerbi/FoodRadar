package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.Serializable

@Serializable
internal data class Rating(
    val rating: Int,
    val score: Double
)