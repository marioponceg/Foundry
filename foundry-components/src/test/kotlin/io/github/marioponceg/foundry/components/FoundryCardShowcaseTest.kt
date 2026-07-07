package io.github.marioponceg.foundry.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import com.github.takahirom.roborazzi.captureRoboImage
import io.github.marioponceg.foundry.tokens.FoundryTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
// sdk pinned to 35: the highest android-all image Robolectric ships (compileSdk is 37).
@Config(sdk = [35])
class FoundryCardShowcaseTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun foundryCardShowcaseDark() {
        composeRule.setContent { FoundryTheme(darkTheme = true) { FoundryCardShowcase() } }
        composeRule.onRoot().captureRoboImage("src/test/screenshots/foundry_card_dark.png")
    }

    @Test
    fun foundryCardShowcaseLight() {
        composeRule.setContent { FoundryTheme(darkTheme = false) { FoundryCardShowcase() } }
        composeRule.onRoot().captureRoboImage("src/test/screenshots/foundry_card_light.png")
    }
}

/** Filled, Outlined, clickable and edge-to-edge cards — the visual contract for FoundryCard. */
@Composable
private fun FoundryCardShowcase() {
    val colors = FoundryTheme.colors
    val spacing = FoundryTheme.spacing
    Column(
        modifier = Modifier
            .background(colors.background)
            .padding(spacing.md)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        FoundryCard(modifier = Modifier.fillMaxWidth()) {
            FoundryText(text = "Filled", style = FoundryTextStyle.Heading, color = colors.onSurface)
            FoundryText(text = "A flat panel on surface.", style = FoundryTextStyle.Body, color = colors.onSurfaceMuted)
        }
        FoundryCard(modifier = Modifier.fillMaxWidth(), style = FoundryCardStyle.Outlined) {
            FoundryText(text = "Outlined", style = FoundryTextStyle.Heading, color = colors.onSurface)
            FoundryText(
                text = "Bordered with the outline token.",
                style = FoundryTextStyle.Body,
                color = colors.onSurfaceMuted,
            )
        }
        FoundryCard(modifier = Modifier.fillMaxWidth(), onClick = {}) {
            FoundryText(text = "Clickable", style = FoundryTextStyle.Heading, color = colors.onSurface)
            FoundryText(text = "Whole-card ripple.", style = FoundryTextStyle.Body, color = colors.onSurfaceMuted)
        }
        FoundryCard(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(0.dp)) {
            FoundryText(text = "Edge-to-edge content", style = FoundryTextStyle.Body, color = colors.onSurface)
        }
    }
}
