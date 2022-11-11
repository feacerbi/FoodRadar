package com.felipeacerbi.foodradar.data_favorite.mapper

import com.felipeacerbi.foodradar.core_favorite.Favorite
import com.felipeacerbi.foodradar.data_favorite.model.FavoriteDto
import org.junit.Assert.assertEquals
import org.junit.Test

internal class FavoriteDtoMapperTest {

    private val mapper = FavoriteDtoMapper()

    @Test
    fun `Given a FavoriteDto Then maps to a Favorite `() {
        val favoriteDto = FavoriteDto(
            id = "2",
            isFavorite = true
        )
        val expectedFavorite = Favorite(
            id = "2",
            isFavorite = true
        )

        val result = mapper.map(favoriteDto)

        assertEquals(expectedFavorite, result)
    }
}