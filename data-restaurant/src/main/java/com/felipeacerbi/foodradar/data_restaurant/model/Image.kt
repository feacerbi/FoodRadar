package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.Serializable

@Serializable
internal data class Image(
    val blurHash: String,
    val url: String,
    val variants: List<String>
)