package com.felipeacerbi.foodradar.data_favorite.di

import com.felipeacerbi.foodradar.core_favorite.FavoriteRepository
import com.felipeacerbi.foodradar.data_favorite.db.FavoriteDao
import com.felipeacerbi.foodradar.data_favorite.db.FavoriteDatabase
import com.felipeacerbi.foodradar.data_favorite.repository.DeviceFavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class FavoriteDataModule {

    @Provides
    fun provideFavoriteDao(
        favoriteDatabase: FavoriteDatabase
    ): FavoriteDao = favoriteDatabase.favoriteDao()

    @Provides
    fun provideFavoriteRepository(
        deviceFavoriteRepository: DeviceFavoriteRepository
    ): FavoriteRepository = deviceFavoriteRepository
}