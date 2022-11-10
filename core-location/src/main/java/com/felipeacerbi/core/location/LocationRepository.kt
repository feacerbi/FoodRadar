package com.felipeacerbi.core.location

import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getCurrentLocation(): Flow<Location>
}