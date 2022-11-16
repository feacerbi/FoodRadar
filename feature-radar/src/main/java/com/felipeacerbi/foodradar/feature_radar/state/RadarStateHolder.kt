package com.felipeacerbi.foodradar.feature_radar.state

import kotlinx.coroutines.flow.Flow

internal interface RadarStateHolder {

    val state: Flow<RadarState>

    fun send(event: Event)

    sealed class Event {
        data class ToggleFavorite(val id: String) : Event()
    }
}