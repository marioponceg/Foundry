package io.github.marioponceg.foundry.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
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
class FoundryButtonShowcaseTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun foundryButtonShowcaseDark() {
        composeRule.setContent { FoundryTheme(darkTheme = true) { FoundryButtonShowcase() } }
        composeRule.onRoot().captureRoboImage("src/test/screenshots/foundry_button_dark.png")
    }

    @Test
    fun foundryButtonShowcaseLight() {
        composeRule.setContent { FoundryTheme(darkTheme = false) { FoundryButtonShowcase() } }
        composeRule.onRoot().captureRoboImage("src/test/screenshots/foundry_button_light.png")
    }
}

/** Every FoundryButtonStyle, plus disabled, loading and leading-icon cases — the visual contract. */
@Composable
private fun FoundryButtonShowcase() {
    val colors = FoundryTheme.colors
    val spacing = FoundryTheme.spacing
    Column(
        modifier = Modifier
            .background(colors.background)
            .padding(spacing.md)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        FoundryButtonStyle.entries.forEach { style ->
            FoundryButton(text = style.name, onClick = {}, style = style)
        }
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
