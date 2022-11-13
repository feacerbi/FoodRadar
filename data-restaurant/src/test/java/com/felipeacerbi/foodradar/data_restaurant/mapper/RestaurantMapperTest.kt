package com.felipeacerbi.foodradar.data_restaurant.mapper

import com.felipeacerbi.foodradar.core_restaurant.Restaurant
import com.felipeacerbi.foodradar.data_restaurant.model.*
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class RestaurantMapperTest {

    private val restaurantMapper = RestaurantMapper()

    @Test
    fun `Given empty sections Then returns empty restaurant list`() {
        val expectedResult = emptyList<Restaurant>()
        val results = mockk<RestaurantResults>().apply {
            every { sections } returns emptyList()
        }

        val result = restaurantMapper.map(results)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `Given no restaurants delivering venues section Then returns empty restaurant list`() {
        val expectedResult = emptyList<Restaurant>()
        val wrongSection = mockk<Section>().apply {
            every { name } returns "wrong-section"
            every { items } returns listOf(mockk())
        }
        val results = mockk<RestaurantResults>().apply {
            every { this@apply.sections } returns listOf(wrongSection)
        }

        val result = restaurantMapper.map(results)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `Given no items in the section Then returns empty restaurant list`() {
        val expectedResult = emptyList<Restaurant>()
        val section = mockk<Section>().apply {
            every { name } returns "restaurants-delivering-venues"
            every { items } returns emptyList()
        }
        val results = mockk<RestaurantResults>().apply {
            every { this@apply.sections } returns listOf(section)
        }

        val result = restaurantMapper.map(results)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `Given items in the section Then returns mapped restaurant list`() {
        val id = "id"
        val name = "name"
        val description = "desc"
        val url = "url"
        val expectedResult = listOf(Restaurant(id, name, description, url))

        val image = mockk<Image>().apply {
            every { this@apply.url } returns url
        }
        val venue = mockk<Venue>().apply {
            every { this@apply.id } returns id
            every { this@apply.name } returns name
            every { this@apply.shortDescription } returns description
        }
        val item = mockk<Item>().apply {
            every { this@apply.venue } returns venue
            every { this@apply.image } returns image
        }
        val section = mockk<Section>().apply {
            every { this@apply.name } returns "restaurants-delivering-venues"
            every { this@apply.items } returns listOf(item)
        }
        val results = mockk<RestaurantResults>().apply {
            every { this@apply.sections } returns listOf(section)
        }

        val result = restaurantMapper.map(results)

        assertEquals(expectedResult, result)
    }
}