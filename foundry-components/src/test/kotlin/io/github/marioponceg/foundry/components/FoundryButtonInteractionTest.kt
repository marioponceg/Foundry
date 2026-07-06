package io.github.marioponceg.foundry.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
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
class FoundryButtonInteractionTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `click fires when enabled and not loading`() {
        var clicks = 0
        composeRule.setContent {
            FoundryTheme { FoundryButton(text = "Tap", onClick = { clicks++ }, modifier = Modifier.testTag("btn")) }
        }
        composeRule.onNodeWithTag("btn").performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun `click is suppressed while loading`() {
        var clicks = 0
        composeRule.setContent {
            FoundryTheme {
                FoundryButton(text = "Tap", onClick = { clicks++ }, loading = true, modifier = Modifier.testTag("btn"))
            }
        }
        composeRule.onNodeWithTag("btn").performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun `click is suppressed when disabled`() {
        var clicks = 0
        composeRule.setContent {
            FoundryTheme {
                FoundryButton(text = "Tap", onClick = { clicks++ }, enabled = false, modifier = Modifier.testTag("btn"))
            }
        }
        composeRule.onNodeWithTag("btn").performClick()
        assertEquals(0, clicks)
    }
}
