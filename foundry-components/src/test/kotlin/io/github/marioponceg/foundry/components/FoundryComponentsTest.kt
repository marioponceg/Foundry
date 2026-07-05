package io.github.marioponceg.foundry.components

import io.github.marioponceg.foundry.tokens.FoundryColors
import kotlin.test.Test
import kotlin.test.assertTrue

class FoundryComponentsTest {

    @Test
    fun `components module sees the tokens module through its api dependency`() {
        val colors = FoundryColors.dark()
        assertTrue(colors.accent != colors.background)
    }
}
