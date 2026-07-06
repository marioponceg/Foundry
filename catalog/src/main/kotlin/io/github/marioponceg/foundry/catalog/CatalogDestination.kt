package io.github.marioponceg.foundry.catalog

/** Catalog screens. Component design units add their destination here plus a registry entry. */
sealed interface CatalogDestination {
    data object Home : CatalogDestination
    data object Tokens : CatalogDestination
}
