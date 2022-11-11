package com.felipeacerbi.foodradar.data_favorite.repository

import com.felipeacerbi.foodradar.core_favorite.Favorite
import com.felipeacerbi.foodradar.core_test.extension.flowTester
import com.felipeacerbi.foodradar.core_test.rule.CoroutinesRule
import com.felipeacerbi.foodradar.data_favorite.db.FavoriteDao
import com.felipeacerbi.foodradar.data_favorite.mapper.FavoriteDtoMapper
import com.felipeacerbi.foodradar.data_favorite.model.FavoriteDto
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class DeviceFavoriteRepositoryTest {

    @get:Rule
    val coroutinesRule = CoroutinesRule()

    private val favoriteDao = mockk<FavoriteDao>()
    private val favoriteDtoMapper = mockk<FavoriteDtoMapper>()

    private val deviceFavoriteRepository = DeviceFavoriteRepository(
        favoriteDao,
        favoriteDtoMapper,
        coroutinesRule.testDispatcher
    )

    private val favoriteDto1 = FavoriteDto("123", true)
    private val favoriteDto2 = FavoriteDto("231", false)
    private val favoriteDto3 = FavoriteDto("321", true)
    private val favorite1 = Favorite("123", true)
    private val favorite2 = Favorite("231", false)
    private val favorite3 = Favorite("321", true)
    private val saved = listOf(favoriteDto1, favoriteDto2, favoriteDto3)

    @Before
    fun setUp() {
        every { favoriteDao.getAll() } returns flowOf(saved)
        every { favoriteDao.insert(any()) } just runs
        every { favoriteDtoMapper.map(favoriteDto1) } returns favorite1
        every { favoriteDtoMapper.map(favoriteDto2) } returns favorite2
        every { favoriteDtoMapper.map(favoriteDto3) } returns favorite3
    }

    @Test
    fun `getFavorites Given saved favorites Then returns favorites list`() = coroutinesRule.runTest {
        val expectedFavorites = listOf(favorite1, favorite2, favorite3)

        val result = flowTester { deviceFavoriteRepository.getFavorites() }

        assertEquals(expectedFavorites, result.values.first())
    }

    @Test
    fun `setFavorite Given id and favorite Then saves to db`() {
        val id = "123"
        val isFavorite = true
        val expectedFavoriteDto = FavoriteDto(id, isFavorite)

        deviceFavoriteRepository.setFavorite(id, isFavorite)

        verify { favoriteDao.insert(expectedFavoriteDto) }
    }
}