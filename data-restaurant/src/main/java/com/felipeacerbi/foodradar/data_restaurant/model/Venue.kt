package com.felipeacerbi.foodradar.data_restaurant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Venue(
    val address: String,
    val badges: List<Badge>,
    val categories: List<String>,
    val city: String,
    val country: String,
    val currency: String,
    val delivers: Boolean,
    @SerialName("delivery_price") val deliveryPrice: String,
    @SerialName("delivery_price_highlight") val deliveryPriceHighlight: Boolean,
    @SerialName("delivery_price_int") val deliveryPriceInt: Int,
    val estimate: Int,
    @SerialName("estimate_range") val estimateRange: String,
    val franchise: String,
    val id: String,
    val location: List<Double>,
    val name: String,
    val online: Boolean,
    @SerialName("price_range") val priceRange: Int,
    @SerialName("product_line") val productLine: String,
    val promotions: List<String>,
    val rating: Rating,
    @SerialName("short_description") val shortDescription: String,
    @SerialName("show_wolt_plus") val showWoltPlus: Boolean,
    val slug: String,
    val tags: List<String>
)