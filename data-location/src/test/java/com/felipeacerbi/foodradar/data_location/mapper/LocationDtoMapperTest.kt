package com.felipeacerbi.foodradar.data_location.mapper

import com.felipeacerbi.foodradar.core_location.Location
import com.felipeacerbi.foodradar.data_location.model.LocationDto
import org.junit.Assert.assertEquals
import org.junit.Test

internal class LocationDtoMapperTest {

    private val mapper = LocationDTOMapper()

    @Test
    fun `Given a LocationDTO with doubles Then maps to a Location with strings`() {
        val locationDTO = LocationDto(
            latitude = 12.3,
            longitude = 15.6
        )
        val expectedLocation = Location(
            latitude = "12.3",
            longitude = "15.6"
        )

        val result = mapper.map(locationDTO)

        assertEquals(expectedLocation, result)
    }
}