package com.felipeacerbi.foodradar.feature_radar.usecase

import com.felipeacerbi.foodradar.core_favorite.Favorite
import com.felipeacerbi.foodradar.core_favorite.FavoriteRepository
import com.felipeacerbi.foodradar.core_test.extension.runTest
import com.felipeacerbi.foodradar.core_test.rule.CoroutinesRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ToggleFavoriteUseCaseTest {

    @get:Rule
    val coroutinesRule = CoroutinesRule()

    private val favoriteRepository = mockk<FavoriteRepository>()

    private val toggleFavoriteUseCase = ToggleFavoriteUseCase(
        favoriteRepository
    )

    @Before
    fun setUp() {
        coEvery { favoriteRepository.setFavorite(any()) } just runs
    }

    @Test
    fun `Given id of non existing favorite Then creates a new one with true`() = runTest {
        val id = "id"
        val favorites = emptyList<Favorite>()
        val expectedFavorite = Favorite(id = id, isFavorite = true)
        coEvery { favoriteRepository.getFavorites() } returns flowOf(favorites)

        toggleFavoriteUseCase(id)

        coVerify { favoriteRepository.setFavorite(expectedFavorite) }
    }

    @Test
    fun `Given id of existing non favorite Then toggles to true`() = runTest {
        val id = "id"
        val favorites = listOf(Favorite(id = id, isFavorite = false))
        val expectedFavorite = Favorite(id = id, isFavorite = true)
        coEvery { favoriteRepository.getFavorites() } returns flowOf(favorites)

        toggleFavoriteUseCase(id)

        coVerify { favoriteRepository.setFavorite(expectedFavorite) }
    }

    @Test
    fun `Given id of existing favorite Then toggles to false`() = runTest {
        val id = "id"
        val favorites = listOf(Favorite(id = id, isFavorite = true))
        val expectedFavorite = Favorite(id = id, isFavorite = false)
        coEvery { favoriteRepository.getFavorites() } returns flowOf(favorites)

        toggleFavoriteUseCase(id)

        coVerify { favoriteRepository.setFavorite(expectedFavorite) }
    }
}