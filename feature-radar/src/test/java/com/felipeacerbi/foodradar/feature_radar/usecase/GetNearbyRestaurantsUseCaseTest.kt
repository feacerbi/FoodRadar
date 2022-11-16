package com.felipeacerbi.foodradar.feature_radar.usecase

import com.felipeacerbi.foodradar.core_location.Location
import com.felipeacerbi.foodradar.core_location.LocationRepository
import com.felipeacerbi.foodradar.core_test.extension.flowTester
import com.felipeacerbi.foodradar.core_test.extension.runTest
import com.felipeacerbi.foodradar.core_test.rule.CoroutinesRule
import com.felipeacerbi.foodradar.feature_radar.mapper.RestaurantResultMapper
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantResult
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantWithFavorite
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetNearbyRestaurantsUseCaseTest {

    @get:Rule
    val coroutinesRule = CoroutinesRule()

    private val getRestaurantsWithFavoritesUseCase = mockk<GetRestaurantsWithFavoritesUseCase>()
    private val locationRepository = mockk<LocationRepository>()
    private val restaurantResultMapper = mockk<RestaurantResultMapper>()

    private val useCase = GetNearbyRestaurantsUseCase(
        getRestaurantsWithFavoritesUseCase,
        locationRepository,
        restaurantResultMapper
    )

    @Test
    fun `Given a new location Then returns a list of nearby restaurants`() = runTest {
        val location = Location("10", "20")
        val restaurantWithFavorite1 = mockk<RestaurantWithFavorite>()
        val restaurantWithFavorite2 = mockk<RestaurantWithFavorite>()
        val restaurantResult1 = mockk<RestaurantResult>()
        val restaurantResult2 = mockk<RestaurantResult>()
        val expectedRestaurantList = listOf(restaurantResult1, restaurantResult2)
        coEvery { locationRepository.getCurrentLocation() } returns flowOf(location)
        every { getRestaurantsWithFavoritesUseCase(location) } returns flowOf(listOf(restaurantWithFavorite1, restaurantWithFavorite2))
        every { restaurantResultMapper.map(restaurantWithFavorite1) } returns restaurantResult1
        every { restaurantResultMapper.map(restaurantWithFavorite2) } returns restaurantResult2

        val result = flowTester { useCase() }

        assertEquals(expectedRestaurantList, result.values[0])
    }

    @Test
    fun `Given 3 new locations Then returns 3 different lists of nearby restaurants`() = runTest {
        val location1 = Location("10", "20")
        val location2 = Location("30", "40")
        val location3 = Location("50", "60")
        val restaurantWithFavorite1 = mockk<RestaurantWithFavorite>()
        val restaurantWithFavorite2 = mockk<RestaurantWithFavorite>()
        val restaurantWithFavorite3 = mockk<RestaurantWithFavorite>()
        val restaurantResult1 = mockk<RestaurantResult>()
        val restaurantResult2 = mockk<RestaurantResult>()
        val restaurantResult3 = mockk<RestaurantResult>()
        val expectedRestaurantLists = listOf(listOf(restaurantResult1), listOf(restaurantResult2), listOf(restaurantResult3))
        coEvery { locationRepository.getCurrentLocation() } returns flow {
            emit(location1)
            emit(location2)
            emit(location3)
        }
        every { getRestaurantsWithFavoritesUseCase(location1) } returns flowOf(listOf(restaurantWithFavorite1))
        every { getRestaurantsWithFavoritesUseCase(location2) } returns flowOf(listOf(restaurantWithFavorite2))
        every { getRestaurantsWithFavoritesUseCase(location3) } returns flowOf(listOf(restaurantWithFavorite3))
        every { restaurantResultMapper.map(restaurantWithFavorite1) } returns restaurantResult1
        every { restaurantResultMapper.map(restaurantWithFavorite2) } returns restaurantResult2
        every { restaurantResultMapper.map(restaurantWithFavorite3) } returns restaurantResult3

        val result = flowTester(count = 3) { useCase() }

        assertEquals(expectedRestaurantLists, result.values)
    }

    @Test
    fun `Given a new location and no results Then returns an empty list of nearby restaurants`() = runTest {
        val location = Location("10", "20")
        val expectedRestaurantList = emptyList<RestaurantResult>()
        coEvery { locationRepository.getCurrentLocation() } returns flowOf(location)
        every { getRestaurantsWithFavoritesUseCase(location) } returns flowOf(emptyList())

        val result = flowTester { useCase() }

        assertEquals(expectedRestaurantList, result.values[0])
    }

    @Test
    fun `Given a new location and 20 results Then returns only 15 results of nearby restaurants`() = runTest {
        val location = Location("10", "20")
        val restaurantsList = (1..20).map { mockk<RestaurantWithFavorite>() }
        coEvery { locationRepository.getCurrentLocation() } returns flowOf(location)
        every { getRestaurantsWithFavoritesUseCase(location) } returns flowOf(restaurantsList)
        every { restaurantResultMapper.map(any()) } returns mockk()

        val result = flowTester { useCase() }

        assertTrue(result.values[0].size == 15)
    }
}