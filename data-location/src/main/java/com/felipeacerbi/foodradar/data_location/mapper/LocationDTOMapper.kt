package com.felipeacerbi.foodradar.data_location.mapper

import com.felipeacerbi.foodradar.core_location.Location
import com.felipeacerbi.foodradar.data_location.model.LocationDto
import javax.inject.Inject

internal class LocationDTOMapper @Inject constructor() {

    fun map(locationDTO: LocationDto): Location =
        Location(
            locationDTO.latitude.toString(),
            locationDTO.longitude.toString()
        )
}