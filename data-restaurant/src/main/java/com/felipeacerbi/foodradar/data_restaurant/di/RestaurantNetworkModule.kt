package com.felipeacerbi.foodradar.data_restaurant.di

import com.felipeacerbi.foodradar.data_restaurant.api.RestaurantApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RestaurantNetworkModule {

    @Provides
    @Singleton
    fun provideRestaurantApi(
        retrofit: Retrofit
    ): RestaurantApi = retrofit.create(RestaurantApi::class.java)
}