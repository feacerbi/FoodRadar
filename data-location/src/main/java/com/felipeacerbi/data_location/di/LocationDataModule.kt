package com.felipeacerbi.data_location.di

import com.felipeacerbi.core.location.LocationRepository
import com.felipeacerbi.data_location.repository.PeriodicLocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class LocationDataModule {

    @Binds
    abstract fun bindLocationRepository(
        periodicLocationRepository: PeriodicLocationRepository
    ): LocationRepository
}