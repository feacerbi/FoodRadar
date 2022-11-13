package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.Serializable

@Serializable
internal data class Filters(
    val filterList: List<Filter>
)