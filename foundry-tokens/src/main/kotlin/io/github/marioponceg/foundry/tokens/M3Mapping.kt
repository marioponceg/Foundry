package io.github.marioponceg.foundry.tokens

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

/**
 * Bridges Foundry tokens into MaterialTheme so wrapped M3 components inherit the Forja
 * styling for free. Mapping only — the Foundry vocabulary is the source of truth, and
 * rarity has deliberately no M3 equivalent.
 */
internal fun foundryColorScheme(colors: FoundryColors, darkTheme: Boolean): ColorScheme {
    val base = if (darkTheme) darkColorScheme() else lightColorScheme()
    return base.copy(
        primary = colors.accent,
        onPrimary = colors.onAccent,
        background = colors.background,
        onBackground = colors.onBackground,
        surface = colors.surface,
        onSurface = colors.onSurface,
        surfaceVariant = colors.surface,
        onSurfaceVariant = colors.onSurfaceMuted,
        error = colors.danger,
        onError = colors.onDanger,
        outline = colors.outline,
    )
}

/**
 * Nearest-slot mapping: a few assignments are approximate by design (e.g. bodyStrong ->
 * bodyMedium, heading -> titleMedium) — M3 has no exact counterpart for those roles.
 */
internal fun foundryM3Typography(typography: FoundryTypography): Typography = Typography(
    displaySmall = typography.display,
    titleLarge = typography.title,
    titleMedium = typography.heading,
    bodyLarge = typography.body,
    bodyMedium = typography.bodyStrong,
    labelLarge = typography.label,
    bodySmall = typography.caption,
)

internal fun foundryM3Shapes(shapes: FoundryShapes): Shapes {
    val defaults = Shapes()
    return Shapes(
        extraSmall = defaults.extraSmall,
        small = shapes.sm as? CornerBasedShape ?: defaults.small,
        medium = shapes.md as? CornerBasedShape ?: defaults.medium,
        large = shapes.lg as? CornerBasedShape ?: defaults.large,
        extraLarge = defaults.extraLarge,
    )
}
