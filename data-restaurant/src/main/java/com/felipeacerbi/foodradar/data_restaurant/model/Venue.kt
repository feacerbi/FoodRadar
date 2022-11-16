package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Venue(
    val address: String = "",
    val badges: List<Badge> = emptyList(),
    val categories: List<String> = emptyList(),
    val city: String = "",
    val country: String = "",
    val currency: String = "",
    val delivers: Boolean = false,
    @SerialName("delivery_price") val deliveryPrice: String = "",
    @SerialName("delivery_price_highlight") val deliveryPriceHighlight: Boolean = false,
    @SerialName("delivery_price_int") val deliveryPriceInt: Int = 0,
    val estimate: Int = 0,
    @SerialName("estimate_range") val estimateRange: String = "",
    val franchise: String = "",
    val id: String = "",
    val location: List<Double> = emptyList(),
    val name: String = "",
    val online: Boolean = false,
    @SerialName("price_range") val priceRange: Int = 0,
    @SerialName("product_line") val productLine: String = "",
    val promotions: List<String> = emptyList(),
    val rating: Rating = Rating(0, 0.0),
    @SerialName("short_description") val shortDescription: String = "",
    @SerialName("show_wolt_plus") val showWoltPlus: Boolean = false,
    val slug: String = "",
    val tags: List<String> = emptyList()
)