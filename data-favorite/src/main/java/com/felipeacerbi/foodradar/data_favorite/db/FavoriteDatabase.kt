package com.felipeacerbi.foodradar.data_favorite.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felipeacerbi.foodradar.data_favorite.model.FavoriteDto

@Database(entities = [FavoriteDto::class], version = 1, exportSchema = false)
internal abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}