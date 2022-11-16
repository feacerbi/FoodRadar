package com.felipeacerbi.foodradar.feature_radar.viewmodel

import com.felipeacerbi.foodradar.core_test.extension.flowTester
import com.felipeacerbi.foodradar.core_test.extension.runTest
import com.felipeacerbi.foodradar.core_test.rule.CoroutinesRule
import com.felipeacerbi.foodradar.feature_radar.mapper.RestaurantUiMapper
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantResult
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantUi
import com.felipeacerbi.foodradar.feature_radar.state.RadarState
import com.felipeacerbi.foodradar.feature_radar.state.RadarStateHolder.Event
import com.felipeacerbi.foodradar.feature_radar.usecase.GetNearbyRestaurantsUseCase
import com.felipeacerbi.foodradar.feature_radar.usecase.ToggleFavoriteUseCase
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RadarViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutinesRule()

    private val getNearbyRestaurantsUseCase = mockk<GetNearbyRestaurantsUseCase>()
    private val toggleFavoriteUseCase = mockk<ToggleFavoriteUseCase>()
    private val restaurantUiMapper = mockk<RestaurantUiMapper>()

    private lateinit var radarViewModel: RadarViewModel

    @Before
    fun setUp() {
        every { getNearbyRestaurantsUseCase() } returns emptyFlow()
    }

    @Test
    fun `Given ToggleFavorite event Then calls the toggle favorite use case`() = runTest {
        val id = "id"
        val event = Event.ToggleFavorite(id)
        coEvery { toggleFavoriteUseCase(any()) } just runs
        setUpViewModel()

        radarViewModel.send(event)

        coVerify { toggleFavoriteUseCase(id) }
    }

    @Test
    fun `Given nearby restaurants Then updates state with restaurant list`() = runTest {
        val restaurantResult = mockk<RestaurantResult>()
        val restaurantResults = listOf(restaurantResult)
        val restaurantUi = mockk<RestaurantUi>()
        val restaurantUiList = listOf(restaurantUi)
        val expectedStates = listOf(
            RadarState.Initial,
            RadarState.Success(restaurantUiList)
        )
        every { getNearbyRestaurantsUseCase() } returns flowOf(restaurantResults)
        every { restaurantUiMapper.map(restaurantResult) } returns restaurantUi
        setUpViewModel()

        val results = flowTester(count = expectedStates.size) {
            radarViewModel.state
        }

        assertEquals(expectedStates, results.values)
    }

    @Test
    fun `Given nearby restaurants error Then updates state with error message`() = runTest {
        val errorMessage = "error"
        val expectedStates = listOf(
            RadarState.Initial,
            RadarState.Error(errorMessage)
        )
        every { getNearbyRestaurantsUseCase() } returns flow { throw Exception(errorMessage) }
        setUpViewModel()

        val results = flowTester(count = expectedStates.size) {
            radarViewModel.state
        }

        assertEquals(expectedStates, results.values)
    }

    private fun setUpViewModel() {
        radarViewModel = RadarViewModel(
            getNearbyRestaurantsUseCase,
            toggleFavoriteUseCase,
            restaurantUiMapper
        )
    }
}