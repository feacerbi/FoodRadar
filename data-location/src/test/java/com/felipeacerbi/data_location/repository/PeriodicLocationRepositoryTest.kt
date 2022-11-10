package com.felipeacerbi.data_location.repository

import com.felipeacerbi.core.location.Location
import com.felipeacerbi.core_test.extension.flowTester
import com.felipeacerbi.core_test.rule.CoroutinesRule
import com.felipeacerbi.data_location.datasource.LocationLocalDataSource
import com.felipeacerbi.data_location.mapper.LocationDTOMapper
import com.felipeacerbi.data_location.model.LocationDTO
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
    private val locationDTOMapper = mockk<LocationDTOMapper>()

    private val periodicLocationRepository = PeriodicLocationRepository(
        localDataSource,
        locationDTOMapper,
        coroutinesRule.testDispatcher
    )

    private val locationDto1 = LocationDTO(2.0, 3.0)
    private val locationDto2 = LocationDTO(1.0, 2.0)
    private val locationDto3 = LocationDTO(4.0, 5.0)
    private val location1 = Location("2.0", "3.0")
    private val location2 = Location("1.0", "2.0")
    private val location3 = Location("4.0", "5.0")
    private val coordinates = listOf(locationDto1, locationDto2, locationDto3)

    @Before
    fun setUp() {
        every { localDataSource.fixedCoordinates } returns coordinates
        every { locationDTOMapper.map(locationDto1) } returns location1
        every { locationDTOMapper.map(locationDto2) } returns location2
        every { locationDTOMapper.map(locationDto3) } returns location3
    }

    @Test
    fun `Given 3 coordinates and 30 seconds Then emits 3 locations`() = coroutinesRule.runTest {
        val expectedLocations = listOf(location1, location2, location3)

        val result = flowTester(count = 3) { periodicLocationRepository.getCurrentLocation() }
        advanceTimeBy(30_000)

        assertEquals(expectedLocations, result.values)
    }

    @Test
    fun `Given 3 coordinates and 10 seconds Then emits 1 location`() = coroutinesRule.runTest {
        val expectedLocations = listOf(location1)

        val result = flowTester(count = 1) { periodicLocationRepository.getCurrentLocation() }
        advanceTimeBy(10_000)

        assertEquals(expectedLocations, result.values)
    }

    @Test
    fun `Given 3 coordinates and 90 seconds Then emits 3 times each location`() = coroutinesRule.runTest {
        val expectedEmissions = 3

        val result = flowTester(count = 9) { periodicLocationRepository.getCurrentLocation() }
        advanceTimeBy(90_000)

        assertEquals(expectedEmissions, result.values.count { it == location1 })
        assertEquals(expectedEmissions, result.values.count { it == location2 })
        assertEquals(expectedEmissions, result.values.count { it == location3 })
    }
}