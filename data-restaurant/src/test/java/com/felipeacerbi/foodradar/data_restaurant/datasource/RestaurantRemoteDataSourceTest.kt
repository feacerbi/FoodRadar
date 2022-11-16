package com.felipeacerbi.foodradar.data_restaurant.datasource

import com.felipeacerbi.foodradar.core_test.extension.runTest
import com.felipeacerbi.foodradar.core_test.rule.CoroutinesRule
import com.felipeacerbi.foodradar.data_restaurant.api.RestaurantApi
import com.felipeacerbi.foodradar.data_restaurant.model.RestaurantResults
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RestaurantRemoteDataSourceTest {

    @get:Rule
    val coroutinesRule = CoroutinesRule()

    private val restaurantApi = mockk<RestaurantApi>()

    private val restaurantRemoteDataSource = RestaurantRemoteDataSource(
        restaurantApi
    )

    @Test
    fun `Given latitude and longitude Then returns results from the api`() = runTest {
        val latitude = 12.5
        val longitude = 20.2
        val expectedResult = mockk<RestaurantResults>()
        coEvery { restaurantApi.getRestaurants(latitude, longitude) } returns expectedResult

        val result = restaurantRemoteDataSource.fetchRestaurants(latitude, longitude)

        assertEquals(expectedResult, result)
    }
}