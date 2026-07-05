package io.github.marioponceg.foundry.components

import io.github.marioponceg.foundry.tokens.Foundry
import kotlin.test.Test
import kotlin.test.assertEquals

class FoundryComponentsTest {

    @Test
    fun `components module sees the tokens module through its api dependency`() {
        assertEquals("Foundry", Foundry.NAME)
    }
}
