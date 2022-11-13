package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ItemFilters(
    val filterList: List<ItemFilter>
)