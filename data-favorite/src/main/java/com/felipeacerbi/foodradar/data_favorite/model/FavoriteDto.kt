package com.felipeacerbi.foodradar.data_favorite.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class FavoriteDto(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean
)
