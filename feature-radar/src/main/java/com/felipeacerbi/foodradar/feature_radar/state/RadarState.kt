package com.felipeacerbi.foodradar.feature_radar.state

import com.felipeacerbi.foodradar.feature_radar.model.RestaurantUi

sealed class RadarState(
    open val restaurants: List<RestaurantUi>,
    open val isLoading: Boolean,
    open val message: String?
) {
    object Initial : RadarState(
        restaurants = emptyList(),
        isLoading = true,
        message = null
    )

    data class Success(override val restaurants: List<RestaurantUi>) : RadarState(
        restaurants = restaurants,
        isLoading = false,
        message = null
    )

    data class Error(override val message: String?) : RadarState(
        restaurants = emptyList(),
        isLoading = false,
        message = message
    )
}
