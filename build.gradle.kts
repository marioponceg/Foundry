// Root-level plugin loading: keeps every plugin in one shared classloader so
// build-logic convention plugins and future root plugins see the same classes
// (same lesson as Conduit's Binary Compatibility Validator setup).
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.detekt) apply false
}
