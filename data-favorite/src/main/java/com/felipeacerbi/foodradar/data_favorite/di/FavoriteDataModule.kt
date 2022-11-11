package com.felipeacerbi.foodradar.data_favorite.di

import android.content.Context
import androidx.room.Room
import com.felipeacerbi.foodradar.data_favorite.db.FavoriteDao
import com.felipeacerbi.foodradar.data_favorite.db.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class FavoriteDataModule {

    @Provides
    @Singleton
    fun provideFavoriteDatabase(
        @ApplicationContext context: Context
    ): FavoriteDatabase =
        Room.databaseBuilder(
            context,
            FavoriteDatabase::class.java,
            "favorite-db"
        ).build()

    @Provides
    fun provideFavoriteDao(
        favoriteDatabase: FavoriteDatabase
    ): FavoriteDao = favoriteDatabase.favoriteDao()
}