package io.github.marioponceg.foundry.catalog.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.marioponceg.foundry.components.FoundryButton
import io.github.marioponceg.foundry.components.FoundryButtonStyle
import io.github.marioponceg.foundry.components.FoundryText
import io.github.marioponceg.foundry.components.FoundryTextStyle
import io.github.marioponceg.foundry.tokens.FoundryTheme

@Composable
internal fun ButtonScreen() {
    val spacing = FoundryTheme.spacing
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        FoundryText(text = "Variants", style = FoundryTextStyle.Heading)
        FoundryButtonStyle.entries.forEach { style ->
            FoundryButton(text = style.name, onClick = {}, style = style)
        }
        FoundryText(text = "States", style = FoundryTextStyle.Heading)
        FoundryButton(text = "Disabled", onClick = {}, enabled = false)
        FoundryButton(text = "Loading", onClick = {}, loading = true)
        FoundryButton(
            text = "With icon",
            onClick = {},
            leadingIcon = {
                Box(modifier = Modifier.size(spacing.md).background(LocalContentColor.current, CircleShape))
            },
        )
    }
}
