package com.felipeacerbi.foodradar.data_location.datasource

import com.felipeacerbi.foodradar.data_location.model.LocationDTO
import javax.inject.Inject

internal class LocationLocalDataSource @Inject constructor() {

    val fixedCoordinates = listOf(
        LocationDTO(60.170187, 24.930599),
        LocationDTO(60.169418, 24.931618),
        LocationDTO(60.169818, 24.932906),
        LocationDTO(60.170005, 24.935105),
        LocationDTO(60.169108, 24.936210),
        LocationDTO(60.168355, 24.934869),
        LocationDTO(60.167560, 24.932562),
        LocationDTO(60.168254, 24.931532),
        LocationDTO(60.169012, 24.930341),
        LocationDTO(60.170085, 24.92956)
    )
}