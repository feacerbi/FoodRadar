package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ItemSortables(
    @SerialName("sortables") val itemSortableList: List<ItemSortable>
)