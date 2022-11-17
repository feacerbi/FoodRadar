package com.felipeacerbi.foodradar.feature_radar.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.felipeacerbi.foodradar.core_design.component.ImageComponent
import com.felipeacerbi.foodradar.core_design.text.BodyMRegular
import com.felipeacerbi.foodradar.core_design.text.BodySRegular
import com.felipeacerbi.foodradar.core_design.text.HeadlineS
import com.felipeacerbi.foodradar.core_design.text.HeadlineXL
import com.felipeacerbi.foodradar.core_design.theming.*
import com.felipeacerbi.foodradar.feature_radar.R
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantUi
import com.felipeacerbi.foodradar.feature_radar.model.RestaurantUi.ToggleState
import com.felipeacerbi.foodradar.feature_radar.state.RadarState
import com.felipeacerbi.foodradar.feature_radar.state.RadarStateHolder.Event.ToggleFavorite
import com.felipeacerbi.foodradar.feature_radar.viewmodel.RadarViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalLifecycleComposeApi
@ExperimentalCoroutinesApi
@Composable
fun RadarScreen() {

    val radarViewModel: RadarViewModel = viewModel()
    val state = radarViewModel.state.collectAsStateWithLifecycle()

    Content(
        state = state.value
    ) { id ->
        radarViewModel.send(ToggleFavorite(id))
    }
}

@Composable
private fun Content(
    state: RadarState,
    onFavoriteToggleClick: (String) -> Unit
) {
    when {
        state.isLoading -> {
            Progress()
        }
        state.message != null -> {
            ErrorMessage(state.message)
        }
        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Accent)
            ) {
                spacer(96.dp)
                restaurantsHeader("Restaurants Nearby")
                spacer(8.dp)
                restaurantsSection(
                    restaurants = state.restaurants.filterEmptyToggle(),
                    emptyText = "No restaurants nearby.",
                    onFavoriteToggleClick = onFavoriteToggleClick
                )
                spacer(32.dp)
                restaurantsHeader("My Favorite Restaurants Nearby")
                spacer(8.dp)
                restaurantsSection(
                    restaurants = state.restaurants.filterFilledToggle(),
                    emptyText = "No favorite restaurants nearby.",
                    onFavoriteToggleClick = onFavoriteToggleClick
                )
                spacer(32.dp)
            }
        }
    }
    Text(
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 32.dp),
        text = "Food Radar",
        style = HeadlineXL,
        color = Primary,
        fontFamily = FontFamily.Cursive
    )
}

private fun LazyListScope.spacer(size: Dp, modifier: Modifier = Modifier) {
    item { Spacer(modifier = modifier.size(size)) }
}

private fun LazyListScope.restaurantsHeader(text: String, modifier: Modifier = Modifier) {
    item {
        Text(
            modifier = modifier.padding(horizontal = 32.dp),
            text = text,
            style = HeadlineS,
            color = Dark
        )
    }
}

private fun LazyListScope.restaurantsSection(
    restaurants: List<RestaurantUi>,
    emptyText: String,
    onFavoriteToggleClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    item {
        if (restaurants.isEmpty()) {
            EmptySection(emptyText)
        } else {
            LazyRow(
                modifier = modifier.defaultMinSize(minHeight = 200.dp),
                contentPadding = PaddingValues(horizontal = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    count = restaurants.size,
                    key = { index -> restaurants[index].id },
                    itemContent = { index ->
                        RestaurantCard(
                            restaurantUi = restaurants[index],
                            onClick = onFavoriteToggleClick
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun EmptySection(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Center
    ) {
        Text(
            text = text,
            style = BodySRegular,
            color = AlmostDark,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun Progress() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Accent),
        contentAlignment = Center
    ) {
        CircularProgressIndicator(color = Primary)
    }
}

@Composable
private fun ErrorMessage(text: String?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Accent),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 32.dp),
            text = text ?: "Unknown error.",
            style = BodyMRegular,
            color = AlmostDark,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RestaurantCard(
    modifier: Modifier = Modifier,
    restaurantUi: RestaurantUi,
    onClick: (String) -> Unit
) {
    ElevatedCard(
        modifier = modifier.width(150.dp),
        shape = ShapeDefaults.Small,
        colors = CardDefaults.cardColors(containerColor = Accent)
    ) {
        Box {
            Column(horizontalAlignment = CenterHorizontally) {
                RestaurantImage(restaurantUi.image)
                Spacer(Modifier.size(24.dp))
                RestaurantTitle(restaurantUi.title)
                Spacer(Modifier.size(8.dp))
                RestaurantSubtitle(restaurantUi.subtitle)
                Spacer(Modifier.size(24.dp))
            }
            FavoriteToggle(restaurantUi = restaurantUi, onClick = onClick)
        }
    }
}

@Composable
private fun RestaurantImage(imageUrl: String, modifier: Modifier = Modifier) {
    ImageComponent(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        imageUrl = imageUrl,
        contentDescription = "Restaurant picture"
    )
}

@Composable
private fun RestaurantTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(horizontal = 8.dp),
        text = text,
        style = HeadlineS,
        color = Dark,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun RestaurantSubtitle(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(horizontal = 8.dp),
        text = text,
        style = BodySRegular,
        color = AlmostDark,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun BoxScope.FavoriteToggle(
    modifier: Modifier = Modifier,
    restaurantUi: RestaurantUi,
    onClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .align(TopEnd)
            .wrapContentSize()
            .background(Brush.radialGradient(listOf(LightScrim, Color.Transparent)))
            .clickable { onClick(restaurantUi.id) }
    ) {
        Icon(
            modifier = Modifier.padding(8.dp),
            imageVector = ImageVector.vectorResource(
                id = when (restaurantUi.toggleState) {
                    ToggleState.Filled -> R.drawable.baseline_favorite
                    ToggleState.Empty -> R.drawable.baseline_favorite_border
                }
            ),
            tint = Primary,
            contentDescription = "Favorite restaurant toggle"
        )
    }
}

private fun List<RestaurantUi>.filterEmptyToggle() = filter { it.toggleState is ToggleState.Empty }
private fun List<RestaurantUi>.filterFilledToggle() = filter { it.toggleState is ToggleState.Filled }

@Preview
@Composable
fun ScreenPreview() {
    Content(
        state = RadarState.Success(
            restaurants = listOf(
                RestaurantUi("id", "KFC", "Italian Cuisine", "", ToggleState.Empty),
                RestaurantUi("id2", "Mc Donalds", "Italian Cuisine", "", ToggleState.Empty),
                RestaurantUi("id3", "Burger King", "Italian Cuisine", "", ToggleState.Filled)
            )
        ),
        onFavoriteToggleClick = {}
    )
}

@Preview
@Composable
fun EmptyScreenPreview() {
    Content(
        state = RadarState.Success(
            restaurants = emptyList()
        ),
        onFavoriteToggleClick = {}
    )
}

@Preview
@Composable
fun LoadingScreenPreview() {
    Content(
        state = RadarState.Initial,
        onFavoriteToggleClick = {}
    )
}

@Preview
@Composable
fun ErrorScreenPreview() {
    Content(
        state = RadarState.Error(
            "Sorry, there was an error fetching the restaurants.\nPlease try again later."
        ),
        onFavoriteToggleClick = {}
    )
}

