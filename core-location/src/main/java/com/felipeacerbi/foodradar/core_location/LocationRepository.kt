package com.felipeacerbi.foodradar.core_location

import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getCurrentLocation(): Flow<Location>
}