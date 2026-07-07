# Foundry

[![Maven Central](https://img.shields.io/maven-central/v/io.github.marioponceg/foundry-components)](https://central.sonatype.com/artifact/io.github.marioponceg/foundry-components)
[![CI](https://github.com/marioponceg/Foundry/actions/workflows/ci.yml/badge.svg)](https://github.com/marioponceg/Foundry/actions/workflows/ci.yml)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)

Design system library for Android built with Jetpack Compose — design tokens, themed components and a consistent visual language, ready to drop into any app.

```kotlin
FoundryTheme {
    FoundryButton(onClick = { /* … */ }) {
        Text("Forge ahead")
    }
}
```

## Why Foundry

Foundry is its own layer on top of Material 3. It defines its own design tokens — colors, typography, spacing, shapes — and a `FoundryTheme` that installs a mapped `MaterialTheme` underneath, so the components (which wrap their M3 counterparts behind Foundry's own API) inherit the styling. You get a consistent visual language, not a thin M3 re-theme.

## Modules

| Module | Description | Published |
|---|---|---|
| `foundry-tokens` | Design tokens (colors, typography, spacing, shapes) and `FoundryTheme`. | Yes |
| `foundry-components` | Themed Compose components: `FoundryText`, `FoundryButton`, `FoundryCard`, `FoundryTextField`. Brings `foundry-tokens` transitively (`api`). | Yes |
| `catalog` | Demo app showcasing every token and component in light/dark. | No |

## Installation

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.marioponceg:foundry-components:0.1.0")
}
```

`foundry-components` exposes `foundry-tokens` transitively, so a single dependency gives you both the components and `FoundryTheme`.

## Requirements

- minSdk 26
- Jetpack Compose
- Java 17

## Catalog

The `catalog` app is the daily development vehicle and a live showcase of every token and component in both themes. Build it with:

```bash
./gradlew :catalog:assembleDebug
```

## License

Foundry is released under the [Apache License 2.0](LICENSE).
