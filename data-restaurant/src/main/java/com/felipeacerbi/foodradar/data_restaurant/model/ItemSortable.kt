package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ItemSortable(
    val id: String,
    val value: Int
)