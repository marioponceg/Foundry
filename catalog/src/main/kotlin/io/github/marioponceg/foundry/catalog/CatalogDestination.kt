package io.github.marioponceg.foundry.catalog

/** Catalog screens. A component design unit adds one data object here (+ its `all` entry). */
sealed interface CatalogDestination {
    val title: String

    data object Home : CatalogDestination {
        override val title: String = "Foundry"
    }

    data object Tokens : CatalogDestination {
        override val title: String = "Tokens"
    }

    data object Text : CatalogDestination {
        override val title: String = "Text"
    }

    companion object {
        /** All destinations, used to save/restore navigation state robustly. */
        val all: List<CatalogDestination> = listOf(Home, Tokens, Text)
    }
}
