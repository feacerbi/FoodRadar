package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Item(
    val description: String,
    val filters: ItemFilters,
    val image: Image,
    val link: Link,
    val overlay: String,
    val quantity: Int,
    @SerialName("quantity_str") val quantityString: String,
    val sortables: ItemSortables,
    val template: String,
    val title: String,
    @SerialName("track_id") val trackId: String,
    val venue: Venue
)