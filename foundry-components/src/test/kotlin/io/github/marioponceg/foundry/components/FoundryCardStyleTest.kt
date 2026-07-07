package io.github.marioponceg.foundry.components

import kotlin.test.Test
import kotlin.test.assertEquals

class FoundryCardStyleTest {

    @Test
    fun `exposes exactly the two card variants`() {
        assertEquals(
            listOf("Filled", "Outlined"),
            FoundryCardStyle.entries.map { it.name },
        )
    }
}
