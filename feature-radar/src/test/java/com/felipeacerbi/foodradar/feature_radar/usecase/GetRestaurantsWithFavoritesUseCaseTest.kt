package com.felipeacerbi.foodradar.feature_radar.usecase

import com.felipeacerbi.foodradar.core_favorite.Favorite
import com.felipeacerbi.foodradar.core_favorite.FavoriteRepository
import com.felipeacerbi.foodradar.core_location.Location
import com.felipeacerbi.foodradar.core_restaurant.Restaurant
import com.felipeacerbi.foodradar.core_restaurant.RestaurantRepository
import com.felipeacerbi.foodradar.core_test.extension.flowTester
import com.felipeacerbi.foodradar.core_test.extension.runTest
import com.felipeacerbi.foodradar.core_test.rule.CoroutinesRule
import com.felipeacerbi.foodradar.feature_radar.mapper.RestaurantWithFavoriteMapper
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantWithFavorite
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetRestaurantsWithFavoritesUseCaseTest {

    @get:Rule
    val coroutinesRule = CoroutinesRule()

    private val favoriteRepository = mockk<FavoriteRepository>()
    private val restaurantRepository = mockk<RestaurantRepository>()
    private val restaurantWithFavoriteMapper = mockk<RestaurantWithFavoriteMapper>()

    private val useCase = GetRestaurantsWithFavoritesUseCase(
        favoriteRepository,
        restaurantRepository,
        restaurantWithFavoriteMapper
    )

    @Test
    fun `Given restaurants and favorites list Then returns a list of restaurants with favorites`() = runTest {
        val latitude = "10"
        val longitude = "20"
        val restaurant = mockk<Restaurant>()
        val favorites = listOf(mockk<Favorite>())
        val restaurantWithFavorite = mockk<RestaurantWithFavorite>()
        val expectedList = listOf(restaurantWithFavorite)
        coEvery { restaurantRepository.getRestaurantsByLocation(latitude, longitude) } returns listOf(restaurant)
        coEvery { favoriteRepository.getFavorites() } returns flowOf(favorites)
        every { restaurantWithFavoriteMapper.map(restaurant, favorites) } returns restaurantWithFavorite

        val result = flowTester { useCase(Location(latitude, longitude)) }

        assertEquals(expectedList, result.values[0])
    }

    @Test
    fun `Given restaurants and favorites changed 3 times Then returns 3 updated lists of restaurants with favorites`() = runTest {
        val latitude = "10"
        val longitude = "20"
        val restaurant = mockk<Restaurant>()
        val favorites1 = listOf(mockk<Favorite>())
        val favorites2 = listOf(mockk<Favorite>())
        val favorites3 = listOf(mockk<Favorite>())
        val restaurantWithFavorite1 = mockk<RestaurantWithFavorite>()
        val restaurantWithFavorite2 = mockk<RestaurantWithFavorite>()
        val restaurantWithFavorite3 = mockk<RestaurantWithFavorite>()
        val expectedLists = listOf(listOf(restaurantWithFavorite1), listOf(restaurantWithFavorite2), listOf(restaurantWithFavorite3))
        coEvery { restaurantRepository.getRestaurantsByLocation(latitude, longitude) } returns listOf(restaurant)
        coEvery { favoriteRepository.getFavorites() } returns flow {
            emit(favorites1)
            emit(favorites2)
            emit(favorites3)
        }
        every { restaurantWithFavoriteMapper.map(restaurant, favorites1) } returns restaurantWithFavorite1
        every { restaurantWithFavoriteMapper.map(restaurant, favorites2) } returns restaurantWithFavorite2
        every { restaurantWithFavoriteMapper.map(restaurant, favorites3) } returns restaurantWithFavorite3

        val result = flowTester(count = 3) { useCase(Location(latitude, longitude)) }

        assertEquals(expectedLists, result.values)
    }
}