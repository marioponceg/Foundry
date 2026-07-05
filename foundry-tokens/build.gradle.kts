plugins {
    id("foundry.library")
}

android {
    namespace = "io.github.marioponceg.foundry.tokens"
    // The bundled Cinzel font ships as an Android resource.
    androidResources {
        enable = true
    }
}
