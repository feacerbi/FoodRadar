package com.felipeacerbi.foodradar.data_location.mapper

import com.felipeacerbi.foodradar.core_location.Location
import com.felipeacerbi.foodradar.data_location.model.LocationDTO
import javax.inject.Inject

internal class LocationDTOMapper @Inject constructor() {

    fun map(locationDTO: LocationDTO): Location =
        Location(
            locationDTO.latitude.toString(),
            locationDTO.longitude.toString()
        )
}