package com.felipeacerbi.foodradar.data_location.repository

import com.felipeacerbi.foodradar.core_location.Location
import com.felipeacerbi.foodradar.core_location.LocationRepository
import com.felipeacerbi.foodradar.data_location.datasource.LocationLocalDataSource
import com.felipeacerbi.foodradar.data_location.di.DefaultDispatcher
import com.felipeacerbi.foodradar.data_location.mapper.LocationMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import javax.inject.Inject

internal class PeriodicLocationRepository @Inject constructor(
    private val locationLocalDataSource: LocationLocalDataSource,
    private val locationMapper: LocationMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : LocationRepository {

    override fun getCurrentLocation(): Flow<Location> = flow {
        val coordinates = locationLocalDataSource.fixedCoordinates

        while (currentCoroutineContext().isActive) {
            for (entry in coordinates) {
                emit(locationMapper.map(entry))
                delay(INTERVAL)
            }
        }
    }.flowOn(defaultDispatcher)

    companion object {
        private const val INTERVAL: Long = 10_000
    }
}