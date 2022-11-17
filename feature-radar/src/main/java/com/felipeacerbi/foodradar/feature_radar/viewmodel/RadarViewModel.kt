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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
internal class RadarViewModel @Inject constructor(
    getNearbyRestaurantsUseCase: GetNearbyRestaurantsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val restaurantUiMapper: RestaurantUiMapper
) : ViewModel(), RadarStateHolder {

    private val internalEvents = MutableSharedFlow<Event>()

    init {
        viewModelScope.launch {
            internalEvents.collectLatest(::handleEvent)
        }
    }

    override val state: StateFlow<RadarState> = getNearbyRestaurantsUseCase()
        .map { results -> results.map(restaurantUiMapper::map) }
        .map { restaurants -> RadarState.Success(restaurants) }
        .onStart<RadarState> { emit(RadarState.Initial) }
        .catch { emit(RadarState.Error(it.message)) }
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