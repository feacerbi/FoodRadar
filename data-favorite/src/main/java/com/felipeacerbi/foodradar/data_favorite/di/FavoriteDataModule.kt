package com.felipeacerbi.foodradar.data_favorite.di

import com.felipeacerbi.foodradar.data_favorite.db.FavoriteDao
import com.felipeacerbi.foodradar.data_favorite.db.FavoriteDatabase
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
}