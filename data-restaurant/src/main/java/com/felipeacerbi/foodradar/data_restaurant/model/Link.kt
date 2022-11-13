package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Link(
    val target: String,
    @SerialName("target_sort") val targetSort: String,
    @SerialName("target_title") val targetTitle: String,
    val title: String,
    val type: String,
    @SerialName("venue_mainimage_blurhash") val venueMainImageBlurHash: String
)