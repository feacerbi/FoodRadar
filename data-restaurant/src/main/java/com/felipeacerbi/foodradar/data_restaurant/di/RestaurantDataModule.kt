package com.felipeacerbi.foodradar.data_restaurant.di

import com.felipeacerbi.foodradar.core_restaurant.RestaurantRepository
import com.felipeacerbi.foodradar.data_restaurant.repository.NearbyRestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class RestaurantDataModule {

    @Provides
    fun provideRestaurantRepository(
        nearbyRestaurantRepository: NearbyRestaurantRepository
    ): RestaurantRepository = nearbyRestaurantRepository
}