package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.Serializable

@Serializable
internal data class Section(
    val items: List<Item>,
    val link: SectionLink,
    val name: String,
    val template: String,
    val title: String
)