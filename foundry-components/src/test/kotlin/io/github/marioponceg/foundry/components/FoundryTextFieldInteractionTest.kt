package io.github.marioponceg.foundry.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import io.github.marioponceg.foundry.tokens.FoundryTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
// sdk pinned to 35: the highest android-all image Robolectric ships (compileSdk is 37).
@Config(sdk = [35])
class FoundryTextFieldInteractionTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `typing delivers the text through onValueChange`() {
        var latest = ""
        composeRule.setContent {
            var value by remember { mutableStateOf("") }
            FoundryTheme {
                FoundryTextField(
                    value = value,
                    onValueChange = {
                        value = it
                        latest = it
                    },
                    modifier = Modifier.testTag("field"),
                )
            }
        }
        composeRule.onNodeWithTag("field").performTextInput("hello")
        assertEquals("hello", latest)
    }
}
