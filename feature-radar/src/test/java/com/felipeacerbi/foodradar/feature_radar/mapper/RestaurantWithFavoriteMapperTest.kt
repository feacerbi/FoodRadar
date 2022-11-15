package com.felipeacerbi.foodradar.feature_radar.mapper

import com.felipeacerbi.foodradar.core_favorite.Favorite
import com.felipeacerbi.foodradar.core_restaurant.Restaurant
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantWithFavorite
import org.junit.Assert.assertEquals
import org.junit.Test

class RestaurantWithFavoriteMapperTest {

    private val mapper = RestaurantWithFavoriteMapper()

    @Test
    fun `Given restaurant and favorites containing it as true Then returns mapped restaurant as favorite`() {
        val id = "id"
        val restaurant = Restaurant(id, "name", "description", "imageUrl")
        val favorites = listOf(Favorite(id, true))
        val expectedResult = RestaurantWithFavorite(
            restaurant = restaurant,
            isFavorite = true
        )

        val result = mapper.map(restaurant, favorites)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `Given restaurant and favorites containing it as false Then returns mapped restaurant as not a favorite`() {
        val id = "id"
        val restaurant = Restaurant(id, "name", "description", "imageUrl")
        val favorites = listOf(Favorite(id, false))
        val expectedResult = RestaurantWithFavorite(
            restaurant = restaurant,
            isFavorite = false
        )

        val result = mapper.map(restaurant, favorites)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `Given restaurant and favorites not containing it Then returns mapped restaurant as not a favorite`() {
        val id = "id"
        val restaurant = Restaurant(id, "name", "description", "imageUrl")
        val favorites = listOf(Favorite("other", true))
        val expectedResult = RestaurantWithFavorite(
            restaurant = restaurant,
            isFavorite = false
        )

        val result = mapper.map(restaurant, favorites)

        assertEquals(expectedResult, result)
    }
}