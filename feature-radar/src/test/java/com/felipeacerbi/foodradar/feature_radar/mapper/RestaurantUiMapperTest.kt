package com.felipeacerbi.foodradar.feature_radar.mapper

import com.felipeacerbi.foodradar.feature_radar.model.RestaurantResult
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantUi
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantUi.ToggleState
import org.junit.Assert.assertEquals
import org.junit.Test

class RestaurantUiMapperTest {

    private val mapper = RestaurantUiMapper()

    private val id = "id"
    private val name = "name"
    private val shortDescription = "description"
    private val imageUrl = "image"

    @Test
    fun `Given a favorite restaurant result Then returns mapper restaurant Ui`() {
        val isFavorite = true
        val restaurantResult = RestaurantResult(
            id = id,
            name = name,
            shortDescription = shortDescription,
            imageUrl = imageUrl,
            isFavorite = isFavorite
        )
        val expectedRestaurantUi = RestaurantUi(
            title = name,
            subtitle = shortDescription,
            image = imageUrl,
            toggleState = ToggleState.Filled
        )

        val result = mapper.map(restaurantResult)

        assertEquals(expectedRestaurantUi, result)
    }

    @Test
    fun `Given a non favorite restaurant result Then returns mapper restaurant Ui`() {
        val isFavorite = false
        val restaurantResult = RestaurantResult(
            id = id,
            name = name,
            shortDescription = shortDescription,
            imageUrl = imageUrl,
            isFavorite = isFavorite
        )
        val expectedRestaurantUi = RestaurantUi(
            title = name,
            subtitle = shortDescription,
            image = imageUrl,
            toggleState = ToggleState.Empty
        )

        val result = mapper.map(restaurantResult)

        assertEquals(expectedRestaurantUi, result)
    }
}