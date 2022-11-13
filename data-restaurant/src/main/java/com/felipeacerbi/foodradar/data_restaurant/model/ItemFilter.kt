package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ItemFilter(
    val id: String,
    val values: List<String>
)