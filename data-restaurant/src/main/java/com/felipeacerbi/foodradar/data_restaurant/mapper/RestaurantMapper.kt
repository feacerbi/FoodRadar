package com.felipeacerbi.foodradar.data_restaurant.mapper

import com.felipeacerbi.foodradar.core_restaurant.Restaurant
import com.felipeacerbi.foodradar.data_restaurant.model.RestaurantResults
import javax.inject.Inject

internal class RestaurantMapper @Inject constructor() {

    fun map(restaurantResults: RestaurantResults): List<Restaurant> =
        restaurantResults
            .sections
            .filter { it.name == RESTAURANTS_DELIVERING_VENUES_SECTION }
            .flatMap { it.items }
            .map { item ->
                Restaurant(
                    item.venue.id,
                    item.venue.name,
                    item.venue.shortDescription,
                    item.image.url
                )
            }

    companion object {
        private const val RESTAURANTS_DELIVERING_VENUES_SECTION = "restaurants-delivering-venues"
    }
}