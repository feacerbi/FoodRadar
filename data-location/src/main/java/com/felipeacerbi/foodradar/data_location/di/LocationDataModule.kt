package com.felipeacerbi.foodradar.data_location.di

import com.felipeacerbi.foodradar.core_location.LocationRepository
import com.felipeacerbi.foodradar.data_location.repository.PeriodicLocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocationDataModule {

    @Binds
    abstract fun bindLocationRepository(
        periodicLocationRepository: PeriodicLocationRepository
    ): LocationRepository
}