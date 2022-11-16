package com.felipeacerbi.foodradar.data_location.repository

import com.felipeacerbi.foodradar.core_location.Location
import com.felipeacerbi.foodradar.core_test.extension.flowTester
import com.felipeacerbi.foodradar.core_test.extension.runTest
import com.felipeacerbi.foodradar.core_test.rule.CoroutinesRule
import com.felipeacerbi.foodradar.data_location.datasource.LocationLocalDataSource
import com.felipeacerbi.foodradar.data_location.mapper.LocationMapper
import com.felipeacerbi.foodradar.data_location.model.LocationDto
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class PeriodicLocationRepositoryTest {

    @get:Rule
    val coroutinesRule = CoroutinesRule()

    private val localDataSource = mockk<LocationLocalDataSource>()
    private val locationMapper = mockk<LocationMapper>()

    private val periodicLocationRepository = PeriodicLocationRepository(
        localDataSource,
        locationMapper,
        coroutinesRule.testDispatcher
    )

    private val locationDto1 = LocationDto(2.0, 3.0)
    private val locationDto2 = LocationDto(1.0, 2.0)
    private val locationDto3 = LocationDto(4.0, 5.0)
    private val location1 = Location("2.0", "3.0")
    private val location2 = Location("1.0", "2.0")
    private val location3 = Location("4.0", "5.0")
    private val coordinates = listOf(locationDto1, locationDto2, locationDto3)

    @Before
    fun setUp() {
        every { localDataSource.fixedCoordinates } returns coordinates
        every { locationMapper.map(locationDto1) } returns location1
        every { locationMapper.map(locationDto2) } returns location2
        every { locationMapper.map(locationDto3) } returns location3
    }

    @Test
    fun `Given 3 coordinates and 30 seconds Then emits 3 locations`() = runTest {
        val expectedLocations = listOf(location1, location2, location3)

        val result = flowTester(count = 3) { periodicLocationRepository.getCurrentLocation() }
        advanceTimeBy(30_000)

        assertEquals(expectedLocations, result.values)
    }

    @Test
    fun `Given 3 coordinates and 10 seconds Then emits 1 location`() = runTest {
        val expectedLocations = listOf(location1)

        val result = flowTester(count = 1) { periodicLocationRepository.getCurrentLocation() }
        advanceTimeBy(10_000)

        assertEquals(expectedLocations, result.values)
    }

    @Test
    fun `Given 3 coordinates and 90 seconds Then emits 3 times each location`() = runTest {
        val expectedEmissions = 3

        val result = flowTester(count = 9) { periodicLocationRepository.getCurrentLocation() }
        advanceTimeBy(90_000)

        assertEquals(expectedEmissions, result.values.count { it == location1 })
        assertEquals(expectedEmissions, result.values.count { it == location2 })
        assertEquals(expectedEmissions, result.values.count { it == location3 })
    }
}