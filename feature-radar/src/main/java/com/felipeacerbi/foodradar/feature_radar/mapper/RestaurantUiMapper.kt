package com.felipeacerbi.foodradar.feature_radar.mapper

import com.felipeacerbi.foodradar.feature_radar.model.RestaurantResult
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantUi
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantUi.ToggleState.Empty
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantUi.ToggleState.Filled

class RestaurantUiMapper {

    fun map(restaurantResult: RestaurantResult): RestaurantUi =
        RestaurantUi(
            title = restaurantResult.name,
            subtitle = restaurantResult.shortDescription,
            image = restaurantResult.imageUrl,
            toggleState = if (restaurantResult.isFavorite) Filled else Empty
        )
}