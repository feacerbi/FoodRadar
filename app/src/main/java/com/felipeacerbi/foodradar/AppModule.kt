package com.felipeacerbi.foodradar

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBaseEndpointUrl(): String = BuildConfig.BASE_ENDPOINT_URL
}