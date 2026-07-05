package io.github.marioponceg.foundry.tokens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalFoundryColors = staticCompositionLocalOf<FoundryColors> {
    error("No FoundryColors provided — wrap your UI in FoundryTheme {}")
}
private val LocalFoundryTypography = staticCompositionLocalOf<FoundryTypography> {
    error("No FoundryTypography provided — wrap your UI in FoundryTheme {}")
}
private val LocalFoundrySpacing = staticCompositionLocalOf<FoundrySpacing> {
    error("No FoundrySpacing provided — wrap your UI in FoundryTheme {}")
}
private val LocalFoundryShapes = staticCompositionLocalOf<FoundryShapes> {
    error("No FoundryShapes provided — wrap your UI in FoundryTheme {}")
}

/**
 * Foundry's single theming entry point. Provides the Foundry tokens through
 * [FoundryTheme]'s accessors and installs a mapped [MaterialTheme] underneath so wrapped
 * M3 components inherit the design language.
 *
 * Retheming: pass your own token instances — e.g. the consuming app can pin its own
 * palette or extend rarity by constructing a custom [FoundryColors].
 */
@Composable
@Suppress("LongParameterList")
public fun FoundryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: FoundryColors = if (darkTheme) FoundryColors.dark() else FoundryColors.light(),
    typography: FoundryTypography = FoundryTypography.default(),
    spacing: FoundrySpacing = FoundrySpacing.default(),
    shapes: FoundryShapes = FoundryShapes.default(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalFoundryColors provides colors,
        LocalFoundryTypography provides typography,
        LocalFoundrySpacing provides spacing,
        LocalFoundryShapes provides shapes,
    ) {
        MaterialTheme(
            colorScheme = foundryColorScheme(colors = colors, darkTheme = darkTheme),
            typography = foundryM3Typography(typography = typography),
            shapes = foundryM3Shapes(shapes = shapes),
            content = content,
        )
    }
}

/** Idiomatic token access: `FoundryTheme.colors.accent`, `FoundryTheme.spacing.md`. */
public object FoundryTheme {
    public val colors: FoundryColors
        @Composable
        @ReadOnlyComposable
        get() = LocalFoundryColors.current
    public val typography: FoundryTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalFoundryTypography.current
    public val spacing: FoundrySpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalFoundrySpacing.current
    public val shapes: FoundryShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalFoundryShapes.current
}
