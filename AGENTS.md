# AGENTS.md

Guidance for AI agents (and humans) working in this repository. This is the source of truth for
project conventions; `CLAUDE.md` simply points here. The full v0.1 design rationale lives in
`docs/superpowers/specs/2026-07-05-foundry-v0.1-design.md`.

## Project

Foundry is a design system library for Android built with Jetpack Compose — design tokens,
themed components and a consistent visual language — built as an open-source **portfolio piece
demonstrating senior-level engineering practices**: API design, Compose idioms, and visual
testing discipline. Its primary audience is technical reviewers and recruiters reading the
commit history, PRs, and code. Sibling project: Conduit (networking library, same conventions).

## Settled design decisions — do not re-litigate, implement as stated

- **Own layer on top of Material 3**: Foundry defines its own tokens
  (`FoundryTheme.colors/typography/spacing/shapes` via CompositionLocals); `FoundryTheme`
  installs a mapped `MaterialTheme` underneath. Components wrap their M3 counterparts behind
  Foundry's own API (typed variants, slots, token-resolved defaults). Foundry neither
  reimplements components from foundation nor is a mere M3 theme.
- **Light + dark from day one**; dynamic color (Material You) is out of scope for v0.1
  (future opt-in).
- **v0.1 components**: FoundryText, FoundryButton, FoundryCard, FoundryTextField — in that
  order, one design unit per PR, each with Roborazzi screenshot tests of every variant in
  light and dark plus its catalog screen.
- **Kotlin explicit API mode** on library modules.
- **No instrumented/emulator tests in v0.1.** No Kover: composables measure poorly —
  Roborazzi visual coverage is the quality bar.

Any design decision **not** listed above must be raised with the maintainer before implementing.

## Module structure

- `foundry-tokens` — the design system core: color schemes (light/dark), typography, spacing,
  shapes, and `FoundryTheme`. Published.
- `foundry-components` — the components, wrapping M3. Exposes `foundry-tokens` as `api`
  (token types appear in component signatures). Published.
- `catalog` — the showcase app (one screen per component + a tokens screen, light/dark
  toggle). **Never published**; it is the daily development vehicle.
- `build-logic` — included build with the `foundry.library` convention plugin (AGP library +
  built-in Kotlin explicit API + Compose + detekt). Library modules apply `foundry.library`
  instead of repeating configuration.
- Wire each new module into `settings.gradle.kts` (`include(...)`).

## Build & toolchain

- JDK 21 (`gradle/gradle-daemon-jvm.properties`), Gradle 9.4.1 (wrapper), AGP 9.2.1 with
  **built-in Kotlin** — never apply `org.jetbrains.kotlin.android`; the Compose compiler
  plugin (`org.jetbrains.kotlin.plugin.compose`, version = bundled Kotlin) is still applied
  per module. Java 17 target for Android modules; minSdk 26, compile/targetSdk 37.
- Compose versions come from the Compose BOM. Dependency versions live in
  `gradle/libs.versions.toml` — never inline version strings in build files.
- Configuration cache is enabled.

## Verification — run before considering any task done

```sh
./gradlew build      # compiles everything and runs all unit tests + Android Lint
./gradlew detekt     # static analysis + formatting (ktlint rules via detekt-formatting)
./gradlew verifyRoborazziDebug   # screenshot tests against committed goldens
```

- Detekt config: `config/detekt/detekt.yml` (builds upon defaults; any unresolved issue fails
  the build — detekt's default since 1.23, no `maxIssues` key needed).
- Golden images live in `<module>/src/test/screenshots/`; regenerate deliberately with
  `./gradlew recordRoborazziDebug` and review the diff before committing.
- A task is not done until these commands pass locally; CI runs the same commands on every PR.

## Commit & branching conventions

- **Conventional Commits** for every commit: `feat:`, `fix:`, `chore:`, `docs:`, `test:`, `refactor:`, `ci:`, `build:` …
- **Trunk-based workflow**: `main` is protected by a repository ruleset — PR required (no
  direct pushes), force-pushes and deletion blocked, CI and PR-title checks must pass. All
  work happens on short-lived feature branches (`feature/<topic>`) merged via PR.
- **Small, reviewable PRs** — one design unit per PR, each including its own tests
  (screenshot tests for anything visual). PR descriptions explain the *why*.
- **PRs are squash-merged.** The PR title becomes the commit on `main`, so it must follow
  Conventional Commits — CI validates this (`.github/workflows/pr-title.yml`).

## Releases, versioning & publishing (lands with the publishing design unit, PR #8)

- SemVer; `VERSION_NAME` in the root `gradle.properties` as source of truth; releases are
  `vX.Y.Z` tags on `main`; publishing to Maven Central under `io.github.marioponceg` via
  `com.vanniktech.maven.publish` + Central Portal with **manual release** — replicating
  Conduit's setup, including: publishing plugin loaded `apply false` at root,
  `--no-configuration-cache` on the Central upload, signing conditional on
  `signingInMemoryKey`, `publishToMavenCentral --dry-run` as config check.

## v0.1 roadmap

| Design unit | Status |
|---|---|
| Tooling: catalog rename, build-logic, module skeletons, CI, AGENTS.md, ruleset | done (PR #1) |
| Tokens + FoundryTheme + Roborazzi | done (PR #2) |
| Catalog base: tokens screen, light/dark toggle | this unit |
| FoundryText → FoundryButton → FoundryCard → FoundryTextField | one PR each |
| Maven Central publishing + release workflow + README → tag v0.1.0 | pending |
