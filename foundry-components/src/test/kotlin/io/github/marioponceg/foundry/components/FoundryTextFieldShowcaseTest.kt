package io.github.marioponceg.foundry.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
class FoundryTextFieldShowcaseTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun foundryTextFieldShowcaseDark() {
        composeRule.setContent { FoundryTheme(darkTheme = true) { FoundryTextFieldShowcase() } }
        composeRule.onRoot().captureRoboImage("src/test/screenshots/foundry_textfield_dark.png")
    }

    @Test
    fun foundryTextFieldShowcaseLight() {
        composeRule.setContent { FoundryTheme(darkTheme = false) { FoundryTextFieldShowcase() } }
        composeRule.onRoot().captureRoboImage("src/test/screenshots/foundry_textfield_light.png")
    }
}

/** Empty, filled, error, disabled and multiline fields — the visual contract for FoundryTextField.
 *  Focus (accent border) is not capturable in a static Roborazzi golden; it is emulator-verified. */
@Composable
private fun FoundryTextFieldShowcase() {
    val colors = FoundryTheme.colors
    val spacing = FoundryTheme.spacing
    Column(
        modifier = Modifier
            .background(colors.background)
            .padding(spacing.md)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacing.md),
    ) {
        FoundryTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = "Label",
            placeholder = "Placeholder",
        )
        FoundryTextField(
            value = "Molten gold",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = "Filled",
        )
        FoundryTextField(
            value = "bad@",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = "Email",
            supportingText = "Invalid email address",
            isError = true,
        )
        FoundryTextField(
            value = "Cannot edit",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = "Disabled",
            enabled = false,
        )
        FoundryTextField(
            value = "A longer note that wraps across multiple lines in the field.",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = "Notes",
            singleLine = false,
        )
    }
}
