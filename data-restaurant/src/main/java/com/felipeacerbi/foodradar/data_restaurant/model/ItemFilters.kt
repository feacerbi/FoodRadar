package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ItemFilters(
    @SerialName("filters") val filterList: List<ItemFilter>
)