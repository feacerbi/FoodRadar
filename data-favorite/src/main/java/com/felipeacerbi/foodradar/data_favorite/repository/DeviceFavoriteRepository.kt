package com.felipeacerbi.foodradar.data_favorite.repository

import com.felipeacerbi.foodradar.core_favorite.Favorite
import com.felipeacerbi.foodradar.core_favorite.FavoriteRepository
import com.felipeacerbi.foodradar.data_favorite.db.FavoriteDao
import com.felipeacerbi.foodradar.data_favorite.mapper.FavoriteDtoMapper
import com.felipeacerbi.foodradar.data_favorite.model.FavoriteDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DeviceFavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val favoriteDtoMapper: FavoriteDtoMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FavoriteRepository {

    override fun getFavorites(): Flow<List<Favorite>> =
        favoriteDao.getAll()
            .map { list -> list.map { favoriteDtoMapper.map(it) } }
            .flowOn(ioDispatcher)

    override fun setFavorite(id: String, isFavorite: Boolean) {
        favoriteDao.insert(FavoriteDto(id, isFavorite))
    }
}