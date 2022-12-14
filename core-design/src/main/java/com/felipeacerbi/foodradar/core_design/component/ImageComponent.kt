package com.felipeacerbi.foodradar.core_design.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun ImageComponent(
    modifier: Modifier,
    imageUrl: String,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String = ""
) {
    AsyncImage(modifier, imageUrl, contentScale, contentDescription)
}

@Composable
private fun AsyncImage(
    modifier: Modifier,
    model: Any,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String = ""
) {
    AsyncImage(
        modifier = modifier,
        contentScale = contentScale,
        model = model,
        contentDescription = contentDescription,
    )
}