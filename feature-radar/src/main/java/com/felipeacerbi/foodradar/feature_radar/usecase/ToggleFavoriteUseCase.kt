package com.felipeacerbi.foodradar.feature_radar.usecase

import com.felipeacerbi.foodradar.core_favorite.Favorite
import com.felipeacerbi.foodradar.core_favorite.FavoriteRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(id: String) {
        val favorites = favoriteRepository.getFavorites().first()
        val currentFavorite = favorites.find { it.id == id }

        val newFavorite = currentFavorite
            .toggleIfExists()
            .orCreateWith(id)

        favoriteRepository.setFavorite(newFavorite)
    }

    private fun Favorite?.toggleIfExists() = this?.copy(isFavorite = !this.isFavorite)

    private fun Favorite?.orCreateWith(id: String) = this ?: Favorite(id = id, isFavorite = true)
}