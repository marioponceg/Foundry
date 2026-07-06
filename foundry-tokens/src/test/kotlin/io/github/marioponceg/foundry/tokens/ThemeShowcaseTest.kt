package io.github.marioponceg.foundry.tokens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class ThemeShowcaseTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun themeShowcaseDark() {
        composeRule.setContent { FoundryTheme(darkTheme = true) { ThemeShowcase() } }
        composeRule.onRoot().captureRoboImage("src/test/screenshots/theme_dark.png")
    }

    @Test
    fun themeShowcaseLight() {
        composeRule.setContent { FoundryTheme(darkTheme = false) { ThemeShowcase() } }
        composeRule.onRoot().captureRoboImage("src/test/screenshots/theme_light.png")
    }
}

/** Internal visual sample: surfaces, the seven text styles, and rarity chips. */
@Composable
private fun ThemeShowcase() {
    val colors = FoundryTheme.colors
    val typography = FoundryTheme.typography
    val spacing = FoundryTheme.spacing
    Column(
        modifier = Modifier
            .background(colors.background)
            .padding(spacing.md)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        Text(text = "Foundry", style = typography.display, color = colors.onBackground)
        Text(text = "Forged in gold and obsidian", style = typography.title, color = colors.accent)
        Text(text = "Heading", style = typography.heading, color = colors.onBackground)
        Text(
            text = "Body — the quick brown fox jumps over the lazy dog.",
            style = typography.body,
            color = colors.onBackground,
        )
        Text(text = "Body strong", style = typography.bodyStrong, color = colors.onBackground)
        Text(text = "LABEL", style = typography.label, color = colors.onSurfaceMuted)
        Text(text = "Caption", style = typography.caption, color = colors.onSurfaceMuted)
        Row(horizontalArrangement = Arrangement.spacedBy(spacing.xs)) {
            listOf(colors.accent, colors.success, colors.warning, colors.danger).forEach { swatch ->
                Box(
                    modifier = Modifier
                        .size(spacing.lg)
                        .background(color = swatch, shape = FoundryTheme.shapes.sm),
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
            listOf(
                "Common" to colors.rarity.common,
                "Uncommon" to colors.rarity.uncommon,
                "Rare" to colors.rarity.rare,
                "Epic" to colors.rarity.epic,
                "Legendary" to colors.rarity.legendary,
            ).forEach { (tier, tierColor) ->
                Text(text = tier, style = typography.label, color = tierColor)
            }
        }
    }
}
