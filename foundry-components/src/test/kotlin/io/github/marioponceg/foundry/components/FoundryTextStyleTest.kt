package io.github.marioponceg.foundry.components

import kotlin.test.Test
import kotlin.test.assertEquals

class FoundryTextStyleTest {

    @Test
    fun `exposes exactly the seven typography roles`() {
        assertEquals(
            listOf("Display", "Title", "Heading", "Body", "BodyStrong", "Label", "Caption"),
            FoundryTextStyle.entries.map { it.name },
        )
    }
}
