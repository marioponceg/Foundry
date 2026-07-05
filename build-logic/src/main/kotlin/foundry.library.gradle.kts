import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs

/**
 * Convention plugin for Foundry library modules: AGP library with built-in Kotlin
 * (explicit API mode), Compose, detekt (with formatting rules), and the shared
 * Compose/test dependencies. Roborazzi and Maven Central publishing join this plugin
 * in their own design units (PRs #2 and #8 of the v0.1 roadmap).
 */
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.compose")
    id("io.gitlab.arturbosch.detekt")
}

val libs = the<LibrariesForLibs>()

extensions.configure<LibraryExtension> {
    compileSdk {
        version = release(37) {
            minorApiLevel = 0
        }
    }
    defaultConfig {
        minSdk = 26
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

// AGP 9 built-in Kotlin registers the `kotlin` extension itself (KotlinAndroidProjectExtension).
kotlin {
    explicitApi()
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom(rootProject.file("config/detekt/detekt.yml"))
}

dependencies {
    "implementation"(platform(libs.androidx.compose.bom))
    "implementation"(libs.androidx.compose.runtime)
    "implementation"(libs.androidx.compose.ui)
    "implementation"(libs.androidx.compose.material3)
    "testImplementation"(libs.junit)
    "testImplementation"(libs.kotlin.test)
    "detektPlugins"(libs.detekt.formatting)
}
