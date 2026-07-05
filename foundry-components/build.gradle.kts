plugins {
    id("foundry.library")
}

android {
    namespace = "io.github.marioponceg.foundry.components"
}

dependencies {
    // Consumers declaring only foundry-components must see the token types
    // (FoundryTheme et al. appear in component signatures), so tokens is `api` —
    // same criterion as conduit-engine-okhttp -> conduit-core.
    api(project(":foundry-tokens"))
}
