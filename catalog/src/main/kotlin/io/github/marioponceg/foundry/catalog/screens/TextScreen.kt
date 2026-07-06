package io.github.marioponceg.foundry.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.marioponceg.foundry.components.FoundryText
import io.github.marioponceg.foundry.components.FoundryTextStyle
import io.github.marioponceg.foundry.tokens.FoundryTheme

@Composable
internal fun TextScreen() {
    val spacing = FoundryTheme.spacing
    val colors = FoundryTheme.colors
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        FoundryText(text = "Styles", style = FoundryTextStyle.Heading)
        FoundryTextStyle.entries.forEach { style ->
            FoundryText(text = style.name, style = style)
        }
        FoundryText(text = "Colors", style = FoundryTextStyle.Heading)
        FoundryText(text = "inherited (default)", style = FoundryTextStyle.Body)
        FoundryText(text = "accent", style = FoundryTextStyle.Body, color = colors.accent)
        FoundryText(text = "legendary rarity", style = FoundryTextStyle.Body, color = colors.rarity.legendary)
    }
}
