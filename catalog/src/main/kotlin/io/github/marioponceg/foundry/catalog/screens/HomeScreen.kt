package io.github.marioponceg.foundry.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.marioponceg.foundry.catalog.CatalogDestination
import io.github.marioponceg.foundry.components.FoundryCard
import io.github.marioponceg.foundry.components.FoundryText
import io.github.marioponceg.foundry.components.FoundryTextStyle
import io.github.marioponceg.foundry.tokens.FoundryTheme

/** One row in the catalog's Home list. Component design units append entries. */
data class CatalogEntry(
    val title: String,
    val description: String,
    val destination: CatalogDestination,
)

internal val catalogEntries = listOf(
    CatalogEntry(
        title = "Tokens",
        description = "Colors, rarity, typography, spacing and shapes",
        destination = CatalogDestination.Tokens,
    ),
    CatalogEntry(
        title = "Text",
        description = "FoundryText styles, colors and overflow",
        destination = CatalogDestination.Text,
    ),
    CatalogEntry(
        title = "Button",
        description = "FoundryButton variants, states and leading icon",
        destination = CatalogDestination.Button,
    ),
    CatalogEntry(
        title = "Card",
        description = "FoundryCard filled, outlined and clickable",
        destination = CatalogDestination.Card,
    ),
    CatalogEntry(
        title = "Text field",
        description = "FoundryTextField label, states and multiline",
        destination = CatalogDestination.TextField,
    ),
)

@Composable
internal fun HomeScreen(onOpen: (CatalogDestination) -> Unit) {
    val colors = FoundryTheme.colors
    val spacing = FoundryTheme.spacing
    LazyColumn(
        contentPadding = androidx.compose.foundation.layout.PaddingValues(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        items(catalogEntries) { entry ->
            FoundryCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onOpen(entry.destination) },
            ) {
                FoundryText(
                    text = entry.title,
                    style = FoundryTextStyle.Heading,
                    color = colors.onSurface,
                )
                FoundryText(
                    text = entry.description,
                    style = FoundryTextStyle.Caption,
                    color = colors.onSurfaceMuted,
                )
            }
        }
    }
}
