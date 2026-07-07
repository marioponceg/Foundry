package io.github.marioponceg.foundry.components

import kotlin.test.Test
import kotlin.test.assertEquals

class FoundryButtonStyleTest {

    @Test
    fun `exposes exactly the four button variants`() {
        assertEquals(
            listOf("Primary", "Secondary", "Tertiary", "Destructive"),
            FoundryButtonStyle.entries.map { it.name },
        )
    }
}
