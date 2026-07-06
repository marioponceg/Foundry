package io.github.marioponceg.foundry.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.text.style.TextOverflow
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
class FoundryTextShowcaseTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun foundryTextShowcaseDark() {
        composeRule.setContent { FoundryTheme(darkTheme = true) { FoundryTextShowcase() } }
        composeRule.onRoot().captureRoboImage("src/test/screenshots/foundry_text_dark.png")
    }

    @Test
    fun foundryTextShowcaseLight() {
        composeRule.setContent { FoundryTheme(darkTheme = false) { FoundryTextShowcase() } }
        composeRule.onRoot().captureRoboImage("src/test/screenshots/foundry_text_light.png")
    }
}

/** Every FoundryTextStyle, plus color and overflow cases — the visual contract for FoundryText. */
@Composable
private fun FoundryTextShowcase() {
    val colors = FoundryTheme.colors
    val spacing = FoundryTheme.spacing
    Column(
        modifier = Modifier
            .background(colors.background)
            .padding(spacing.md)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacing.xs),
    ) {
        FoundryTextStyle.entries.forEach { style ->
            FoundryText(text = style.name, style = style)
        }
        FoundryText(text = "accent color", style = FoundryTextStyle.Body, color = colors.accent)
        FoundryText(text = "epic rarity", style = FoundryTextStyle.Body, color = colors.rarity.epic)
        FoundryText(
            text = "a very long single line that must be truncated with an ellipsis at the end",
            style = FoundryTextStyle.Body,
            color = colors.onSurfaceMuted,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(160.dp),
        )
    }
}
