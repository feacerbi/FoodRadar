package com.felipeacerbi.foodradar.data_restaurant.repository

import com.felipeacerbi.foodradar.core_restaurant.Restaurant
import com.felipeacerbi.foodradar.core_test.extension.runTest
import com.felipeacerbi.foodradar.core_test.rule.CoroutinesRule
import com.felipeacerbi.foodradar.data_restaurant.datasource.RestaurantRemoteDataSource
import com.felipeacerbi.foodradar.data_restaurant.mapper.RestaurantMapper
import com.felipeacerbi.foodradar.data_restaurant.model.RestaurantResults
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GeolocationRestaurantRepositoryTest {

    @get:Rule
    val coroutinesRule = CoroutinesRule()

    private val restaurantRemoteDataSource = mockk<RestaurantRemoteDataSource>()
    private val restaurantMapper = mockk<RestaurantMapper>()

    private val geolocationRestaurantRepository = GeolocationRestaurantRepository(
        restaurantRemoteDataSource,
        restaurantMapper,
        coroutinesRule.testDispatcher
    )

    @Test
    fun `Given latitude and longitude Then returns mapped results from the data source`() = runTest {
        val latitude = "12.5"
        val longitude = "20.2"
        val latitudeDouble = 12.5
        val longitudeDouble = 20.2
        val results = mockk<RestaurantResults>()
        val restaurant = mockk<Restaurant>()
        val expectedResult = listOf(restaurant)
        coEvery { restaurantRemoteDataSource.fetchRestaurants(latitudeDouble, longitudeDouble) } returns results
        coEvery { restaurantMapper.map(results) } returns expectedResult

        val result = geolocationRestaurantRepository.getRestaurantsByLocation(latitude, longitude)

        assertEquals(expectedResult, result)
    }
}