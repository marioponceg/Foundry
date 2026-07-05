package io.github.marioponceg.foundry.tokens

import kotlin.test.Test
import kotlin.test.assertEquals

class FoundryTest {

    @Test
    fun `design system marker exposes its name`() {
        assertEquals("Foundry", Foundry.NAME)
    }
}
