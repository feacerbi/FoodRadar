package com.felipeacerbi.data_location.repository

import com.felipeacerbi.core.location.Location
import com.felipeacerbi.core.location.LocationRepository
import com.felipeacerbi.data_location.datasource.LocationLocalDataSource
import com.felipeacerbi.data_location.mapper.LocationDTOMapper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class PeriodicLocationRepository @Inject constructor(
    private val locationLocalDataSource: LocationLocalDataSource,
    private val locationDTOMapper: LocationDTOMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocationRepository {

    override fun getCurrentLocation(): Flow<Location> = flow {
        val coordinates = locationLocalDataSource.fixedCoordinates

        while (currentCoroutineContext().isActive) {
            for (entry in coordinates) {
                emit(locationDTOMapper.map(entry))
                delay(INTERVAL)
            }
        }
    }.flowOn(ioDispatcher)

    companion object {
        private const val INTERVAL: Long = 10_000
    }
}