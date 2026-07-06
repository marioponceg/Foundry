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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.marioponceg.foundry.catalog.screens.HomeScreen
import io.github.marioponceg.foundry.tokens.FoundryTheme

private val DestinationSaver = Saver<CatalogDestination, String>(
    save = { destination -> if (destination is CatalogDestination.Tokens) "tokens" else "home" },
    restore = { saved -> if (saved == "tokens") CatalogDestination.Tokens else CatalogDestination.Home },
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
                title = if (destination is CatalogDestination.Tokens) "Tokens" else "Foundry",
                showBack = destination != CatalogDestination.Home,
                darkTheme = darkTheme,
                onBack = { destination = CatalogDestination.Home },
                onToggleTheme = { darkTheme = !darkTheme },
            )
            when (destination) {
                CatalogDestination.Home -> HomeScreen(onOpen = { destination = it })
                CatalogDestination.Tokens -> Text(
                    text = "Tokens screen arrives in the next task",
                    style = FoundryTheme.typography.body,
                    color = FoundryTheme.colors.onBackground,
                    modifier = Modifier.padding(FoundryTheme.spacing.md),
                )
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
            Text(
                text = "Back",
                style = FoundryTheme.typography.label,
                color = colors.accent,
                modifier = Modifier
                    .clickable(onClick = onBack)
                    .padding(end = spacing.md),
            )
        }
        Text(
            text = title,
            style = FoundryTheme.typography.title,
            color = colors.onBackground,
            modifier = Modifier.weight(1f),
        )
        Spacer(modifier = Modifier.padding(start = spacing.sm))
        Text(
            text = if (darkTheme) "Light" else "Dark",
            style = FoundryTheme.typography.label,
            color = colors.accent,
            modifier = Modifier.clickable(onClick = onToggleTheme),
        )
    }
}
