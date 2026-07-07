package io.github.marioponceg.foundry.catalog

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.marioponceg.foundry.catalog.screens.ButtonScreen
import io.github.marioponceg.foundry.catalog.screens.HomeScreen
import io.github.marioponceg.foundry.catalog.screens.TextScreen
import io.github.marioponceg.foundry.catalog.screens.TokensScreen
import io.github.marioponceg.foundry.components.FoundryText
import io.github.marioponceg.foundry.components.FoundryTextStyle
import io.github.marioponceg.foundry.tokens.FoundryTheme

private val DestinationSaver = Saver<CatalogDestination, String>(
    save = { destination -> destination.title },
    restore = { saved -> CatalogDestination.all.firstOrNull { it.title == saved } ?: CatalogDestination.Home },
)

@Composable
internal fun CatalogApp() {
    val systemDark = isSystemInDarkTheme()
    var darkTheme by rememberSaveable { mutableStateOf(systemDark) }
    var destination by rememberSaveable(stateSaver = DestinationSaver) {
        mutableStateOf<CatalogDestination>(CatalogDestination.Home)
    }
    FoundryTheme(darkTheme = darkTheme) {
        BackHandler(enabled = destination != CatalogDestination.Home) {
            destination = CatalogDestination.Home
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(FoundryTheme.colors.background)
                .statusBarsPadding(),
        ) {
            CatalogTopBar(
                title = destination.title,
                showBack = destination != CatalogDestination.Home,
                darkTheme = darkTheme,
                onBack = { destination = CatalogDestination.Home },
                onToggleTheme = { darkTheme = !darkTheme },
            )
            when (destination) {
                CatalogDestination.Home -> HomeScreen(onOpen = { destination = it })
                CatalogDestination.Tokens -> TokensScreen()
                CatalogDestination.Text -> TextScreen()
                CatalogDestination.Button -> ButtonScreen()
            }
        }
    }
}

@Composable
private fun CatalogTopBar(
    title: String,
    showBack: Boolean,
    darkTheme: Boolean,
    onBack: () -> Unit,
    onToggleTheme: () -> Unit,
) {
    val colors = FoundryTheme.colors
    val spacing = FoundryTheme.spacing
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.md, vertical = spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showBack) {
            FoundryText(
                text = "Back",
                style = FoundryTextStyle.Label,
                color = colors.accent,
                modifier = Modifier
                    .clickable(onClick = onBack)
                    .padding(end = spacing.md),
            )
        }
        FoundryText(
            text = title,
            style = FoundryTextStyle.Title,
            color = colors.onBackground,
            modifier = Modifier.weight(1f),
        )
        Spacer(modifier = Modifier.padding(start = spacing.sm))
        FoundryText(
            text = if (darkTheme) "Light" else "Dark",
            style = FoundryTextStyle.Label,
            color = colors.accent,
            modifier = Modifier.clickable(onClick = onToggleTheme),
        )
    }
}
