package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.Serializable

@Serializable
internal data class Sortable(
    val id: String,
    val name: String,
    val type: String
)