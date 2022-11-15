package com.felipeacerbi.foodradar.data_restaurant.di

import com.felipeacerbi.foodradar.core_restaurant.RestaurantRepository
import com.felipeacerbi.foodradar.data_restaurant.repository.GeolocationRestaurantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class RestaurantDataModule {

    @Binds
    abstract fun bindRestaurantRepository(
        geolocationRestaurantRepository: GeolocationRestaurantRepository
    ): RestaurantRepository
}