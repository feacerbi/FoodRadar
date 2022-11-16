package com.felipeacerbi.foodradar.feature_radar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeacerbi.foodradar.feature_radar.mapper.RestaurantUiMapper
import com.felipeacerbi.foodradar.feature_radar.state.RadarState
import com.felipeacerbi.foodradar.feature_radar.state.RadarStateHolder
import com.felipeacerbi.foodradar.feature_radar.state.RadarStateHolder.Event
import com.felipeacerbi.foodradar.feature_radar.state.RadarStateHolder.Event.ToggleFavorite
import com.felipeacerbi.foodradar.feature_radar.usecase.GetNearbyRestaurantsUseCase
import com.felipeacerbi.foodradar.feature_radar.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
internal class RadarViewModel(
    getNearbyRestaurantsUseCase: GetNearbyRestaurantsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val restaurantUiMapper: RestaurantUiMapper
) : RadarStateHolder, ViewModel() {

    private val internalEvents = MutableSharedFlow<Event>()

    init {
        viewModelScope.launch {
            internalEvents.collectLatest(::handleEvent)
        }
    }

    override val state: Flow<RadarState> = getNearbyRestaurantsUseCase()
        .map { results -> results.map(restaurantUiMapper::map) }
        .map { restaurants -> RadarState.Success(restaurants) }
        .catch<RadarState> { emit(RadarState.Error(it.message)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(FLOW_SUBSCRIPTION_TIMEOUT),
            initialValue = RadarState.Initial
        )

    override fun send(event: Event) {
        viewModelScope.launch {
            internalEvents.emit(event)
        }
    }

    private fun handleEvent(event: Event) =
        when (event) {
            is ToggleFavorite -> toggleFavorite(event.id)
        }

    private fun toggleFavorite(id: String) = viewModelScope.launch {
        toggleFavoriteUseCase(id)
    }

    companion object {
        private const val FLOW_SUBSCRIPTION_TIMEOUT = 5_000L
    }
}