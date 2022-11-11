package com.felipeacerbi.foodradar.data_favorite.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.felipeacerbi.foodradar.data_favorite.model.FavoriteDto
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FavoriteDao {

    @Query("SELECT * FROM favoriteDto")
    fun getAll(): Flow<List<FavoriteDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteDto: FavoriteDto)
}