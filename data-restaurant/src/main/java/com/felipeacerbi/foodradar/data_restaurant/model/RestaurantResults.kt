package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RestaurantResults(
    val created: Created,
    @SerialName("expires_in_seconds") val expiresInSeconds: Int,
    @SerialName("filtering") val filters: Filters,
    val name: String,
    @SerialName("page_title") val pageTitle: String,
    val sections: List<Section>,
    @SerialName("show_large_title") val showLargeTitle: Boolean,
    @SerialName("show_map") val showMap: Boolean,
    val sorting: Sortables,
    @SerialName("track_id") val trackId: String
)