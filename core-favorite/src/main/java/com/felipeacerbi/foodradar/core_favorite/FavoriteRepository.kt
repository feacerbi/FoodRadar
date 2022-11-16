package com.felipeacerbi.foodradar.core_favorite

import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorites(): Flow<List<Favorite>>
    fun setFavorite(favorite: Favorite)
}