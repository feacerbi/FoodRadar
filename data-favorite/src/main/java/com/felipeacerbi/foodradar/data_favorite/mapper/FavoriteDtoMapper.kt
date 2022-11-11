package com.felipeacerbi.foodradar.data_favorite.mapper

import com.felipeacerbi.foodradar.core_favorite.Favorite
import com.felipeacerbi.foodradar.data_favorite.model.FavoriteDto
import javax.inject.Inject

internal class FavoriteDtoMapper @Inject constructor() {

    fun map(favoriteDto: FavoriteDto): Favorite =
        Favorite(
            id = favoriteDto.id,
            isFavorite = favoriteDto.isFavorite
        )
}