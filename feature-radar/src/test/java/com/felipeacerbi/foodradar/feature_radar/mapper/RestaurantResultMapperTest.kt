package com.felipeacerbi.foodradar.feature_radar.mapper

import com.felipeacerbi.foodradar.core_restaurant.Restaurant
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantResult
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantWithFavorite
import org.junit.Assert.assertEquals
import org.junit.Test

class RestaurantResultMapperTest {

    private val mapper = RestaurantResultMapper()

    @Test
    fun `Given restaurant with favorite Then returns mapped restaurant result`() {
        val id = "id"
        val name = "name"
        val description = "description"
        val imageUrl = "imageUrl"
        val isFavorite = true
        val restaurant = Restaurant(id, name, description, imageUrl)
        val restaurantWithFavorite = RestaurantWithFavorite(restaurant, isFavorite)
        val expectedRestaurantResult = RestaurantResult(
            id,
            name,
            description,
            imageUrl,
            isFavorite
        )

        val result = mapper.map(restaurantWithFavorite)

        assertEquals(expectedRestaurantResult, result)
    }
}