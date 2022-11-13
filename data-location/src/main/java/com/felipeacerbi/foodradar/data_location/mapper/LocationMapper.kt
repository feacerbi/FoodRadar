package com.felipeacerbi.foodradar.data_location.mapper

import com.felipeacerbi.foodradar.core_location.Location
import com.felipeacerbi.foodradar.data_location.model.LocationDto
import javax.inject.Inject

internal class LocationMapper @Inject constructor() {

    fun map(locationDto: LocationDto): Location =
        Location(
            locationDto.latitude.toString(),
            locationDto.longitude.toString()
        )
}