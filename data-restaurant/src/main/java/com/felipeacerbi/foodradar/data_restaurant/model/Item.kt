package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Item(
    val description: String = "",
    @SerialName("filtering") val filters: ItemFilters = ItemFilters(emptyList()),
    val image: Image,
    val link: Link,
    val overlay: String = "",
    val quantity: Int = 0,
    @SerialName("quantity_str") val quantityString: String = "",
    @SerialName("sorting") val sortables: ItemSortables = ItemSortables(emptyList()),
    val template: String,
    val title: String,
    @SerialName("track_id") val trackId: String,
    val venue: Venue = Venue()
)