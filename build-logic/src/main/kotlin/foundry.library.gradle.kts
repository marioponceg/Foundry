import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs

/**
 * Convention plugin for Foundry library modules: AGP library with built-in Kotlin
 * (explicit API mode), Compose, detekt (with formatting rules), and the shared
 * Compose/test dependencies. Maven Central publishing via the vanniktech plugin
 * (POM metadata resolved from gradle.properties).
 */
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.compose")
    id("io.gitlab.arturbosch.detekt")
    id("io.github.takahirom.roborazzi")
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    // Uploads to the Central Portal; actual publication is released manually from
    // central.sonatype.com (publishing to Maven Central is irreversible by design).
    publishToMavenCentral()
    // Signing is mandatory for Maven Central but must not block local publishToMavenLocal:
    // sign only when the in-memory key is present (always true in the release workflow).
    if (providers.gradleProperty("signingInMemoryKey").isPresent) {
        signAllPublications()
    }
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
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
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
    "testImplementation"(libs.robolectric)
    "testImplementation"(libs.roborazzi)
    "testImplementation"(libs.roborazzi.compose)
    "testImplementation"(libs.roborazzi.junit.rule)
    "testImplementation"(libs.androidx.compose.ui.test.junit4)
    "testImplementation"(libs.androidx.compose.ui.test.manifest)
    "detektPlugins"(libs.detekt.formatting)
}
