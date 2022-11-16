package com.felipeacerbi.foodradar.data_favorite.mapper

import com.felipeacerbi.foodradar.core_favorite.Favorite
import com.felipeacerbi.foodradar.data_favorite.model.FavoriteDto
import javax.inject.Inject

internal class FavoriteMapper @Inject constructor() {

    fun map(favoriteDto: FavoriteDto): Favorite =
        Favorite(
            id = favoriteDto.id,
            isFavorite = favoriteDto.isFavorite
        )

    fun map(favorite: Favorite): FavoriteDto =
        FavoriteDto(
            id = favorite.id,
            isFavorite = favorite.isFavorite
        )
}