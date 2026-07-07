package io.github.marioponceg.foundry.catalog

import org.junit.Assert.assertEquals
import org.junit.Test

class CatalogDestinationTest {

    @Test
    fun `all destination titles are unique`() {
        val titles = CatalogDestination.all.map { it.title }
        assertEquals(titles, titles.distinct())
    }
}
