package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Image(
    @SerialName("blurhash") val blurHash: String,
    val url: String,
    val variants: List<String> = emptyList()
)