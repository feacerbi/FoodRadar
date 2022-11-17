package com.felipeacerbi.foodradar

import com.felipeacerbi.foodradar.core_network.BaseEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @BaseEndpoint
    fun provideBaseEndpointUrl(): String = BuildConfig.BASE_ENDPOINT_URL
}