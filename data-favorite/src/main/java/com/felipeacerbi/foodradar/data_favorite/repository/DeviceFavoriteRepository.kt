package com.felipeacerbi.foodradar.data_favorite.repository

import com.felipeacerbi.foodradar.core_favorite.Favorite
import com.felipeacerbi.foodradar.core_favorite.FavoriteRepository
import com.felipeacerbi.foodradar.data_favorite.db.FavoriteDao
import com.felipeacerbi.foodradar.data_favorite.di.IoDispatcher
import com.felipeacerbi.foodradar.data_favorite.mapper.FavoriteMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DeviceFavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val favoriteMapper: FavoriteMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FavoriteRepository {

    override fun getFavorites(): Flow<List<Favorite>> =
        favoriteDao.getAll()
            .map { list -> list.map(favoriteMapper::map) }
            .flowOn(ioDispatcher)

    override suspend fun setFavorite(favorite: Favorite) = withContext(ioDispatcher) {
        favoriteDao.insert(favoriteMapper.map(favorite))
    }
}