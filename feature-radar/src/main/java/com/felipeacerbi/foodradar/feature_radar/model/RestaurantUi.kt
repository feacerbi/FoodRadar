package com.felipeacerbi.foodradar.feature_radar.model

data class RestaurantUi(
    val title: String,
    val subtitle: String,
    val image: String,
    val toggleState: ToggleState
) {
    sealed class ToggleState {
        object Filled : ToggleState()
        object Empty : ToggleState()
    }
}
