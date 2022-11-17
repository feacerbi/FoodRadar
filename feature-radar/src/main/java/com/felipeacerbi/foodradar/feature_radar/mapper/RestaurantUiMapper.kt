package com.felipeacerbi.foodradar.feature_radar.mapper

import com.felipeacerbi.foodradar.feature_radar.model.RestaurantResult
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantUi
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantUi.ToggleState.Empty
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantUi.ToggleState.Filled
import javax.inject.Inject

internal class RestaurantUiMapper @Inject constructor() {

    fun map(restaurantResult: RestaurantResult): RestaurantUi =
        RestaurantUi(
            id = restaurantResult.id,
            title = restaurantResult.name,
            subtitle = restaurantResult.shortDescription,
            image = restaurantResult.imageUrl,
            toggleState = if (restaurantResult.isFavorite) Filled else Empty
        )
}