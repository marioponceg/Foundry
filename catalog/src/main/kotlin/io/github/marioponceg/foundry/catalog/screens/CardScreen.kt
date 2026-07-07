package io.github.marioponceg.foundry.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.marioponceg.foundry.components.FoundryCard
import io.github.marioponceg.foundry.components.FoundryCardStyle
import io.github.marioponceg.foundry.components.FoundryText
import io.github.marioponceg.foundry.components.FoundryTextStyle
import io.github.marioponceg.foundry.tokens.FoundryTheme

@Composable
internal fun CardScreen() {
    val colors = FoundryTheme.colors
    val spacing = FoundryTheme.spacing
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        FoundryCard(modifier = Modifier.fillMaxWidth()) {
            FoundryText(text = "Filled card", style = FoundryTextStyle.Heading, color = colors.onSurface)
            FoundryText(
                text = "Container on surface, flat.",
                style = FoundryTextStyle.Body,
                color = colors.onSurfaceMuted,
            )
        }
        FoundryCard(modifier = Modifier.fillMaxWidth(), style = FoundryCardStyle.Outlined) {
            FoundryText(text = "Outlined card", style = FoundryTextStyle.Heading, color = colors.onSurface)
            FoundryText(
                text = "Bordered with the outline token.",
                style = FoundryTextStyle.Body,
                color = colors.onSurfaceMuted,
            )
        }
        FoundryCard(modifier = Modifier.fillMaxWidth(), onClick = {}) {
            FoundryText(text = "Clickable card", style = FoundryTextStyle.Heading, color = colors.onSurface)
            FoundryText(
                text = "Tap for a whole-card ripple.",
                style = FoundryTextStyle.Body,
                color = colors.onSurfaceMuted,
            )
        }
    }
}
