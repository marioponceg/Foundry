package io.github.marioponceg.foundry.catalog.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.marioponceg.foundry.tokens.FoundryTheme

@Composable
internal fun TokensScreen() {
    val spacing = FoundryTheme.spacing
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.lg),
    ) {
        ColorsSection()
        RaritySection()
        TypographySection()
        SpacingSection()
        ShapesSection()
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = FoundryTheme.typography.heading,
        color = FoundryTheme.colors.onBackground,
    )
}

@Composable
private fun ColorsSection() {
    val colors = FoundryTheme.colors
    Column(verticalArrangement = Arrangement.spacedBy(FoundryTheme.spacing.sm)) {
        SectionTitle(text = "Colors")
        listOf(
            Triple("background", colors.background, colors.onBackground),
            Triple("surface", colors.surface, colors.onSurface),
            Triple("accent", colors.accent, colors.onAccent),
            Triple("success", colors.success, colors.onSuccess),
            Triple("warning", colors.warning, colors.onWarning),
            Triple("danger", colors.danger, colors.onDanger),
        ).forEach { (name, color, onColor) ->
            ColorSwatch(name = name, color = color, onColor = onColor)
        }
    }
}

@Composable
private fun ColorSwatch(name: String, color: Color, onColor: Color) {
    val spacing = FoundryTheme.spacing
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = color, shape = FoundryTheme.shapes.sm)
            .border(
                width = Dp.Hairline,
                color = FoundryTheme.colors.outline,
                shape = FoundryTheme.shapes.sm,
            )
            .padding(spacing.sm),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = name, style = FoundryTheme.typography.label, color = onColor)
        Text(text = color.toHex(), style = FoundryTheme.typography.caption, color = onColor)
    }
}

private fun Color.toHex(): String {
    val argb = this.toArgb()
    return "#%06X".format(argb and 0xFFFFFF)
}

@Composable
private fun RaritySection() {
    val colors = FoundryTheme.colors
    val spacing = FoundryTheme.spacing
    Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
        SectionTitle(text = "Rarity")
        listOf(
            "Common" to colors.rarity.common,
            "Uncommon" to colors.rarity.uncommon,
            "Rare" to colors.rarity.rare,
            "Epic" to colors.rarity.epic,
            "Legendary" to colors.rarity.legendary,
        ).forEach { (tier, tierColor) ->
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.md)) {
                Text(
                    text = tier,
                    style = FoundryTheme.typography.bodyStrong,
                    color = tierColor,
                    modifier = Modifier.weight(1f),
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = colors.surface, shape = FoundryTheme.shapes.sm)
                        .padding(spacing.xs),
                ) {
                    Text(
                        text = "on surface",
                        style = FoundryTheme.typography.label,
                        color = tierColor,
                    )
                }
            }
        }
    }
}

@Composable
private fun TypographySection() {
    val typography = FoundryTheme.typography
    val colors = FoundryTheme.colors
    Column(verticalArrangement = Arrangement.spacedBy(FoundryTheme.spacing.sm)) {
        SectionTitle(text = "Typography")
        listOf(
            "display 36sp" to typography.display,
            "title 24sp" to typography.title,
            "heading 18sp" to typography.heading,
            "body 16sp" to typography.body,
            "bodyStrong 16sp" to typography.bodyStrong,
            "label 14sp" to typography.label,
            "caption 12sp" to typography.caption,
        ).forEach { (name, style) ->
            Text(text = name, style = style, color = colors.onBackground)
        }
    }
}

@Composable
private fun SpacingSection() {
    val spacing = FoundryTheme.spacing
    val colors = FoundryTheme.colors
    Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
        SectionTitle(text = "Spacing")
        listOf(
            "xs 4dp" to spacing.xs,
            "sm 8dp" to spacing.sm,
            "md 16dp" to spacing.md,
            "lg 24dp" to spacing.lg,
            "xl 32dp" to spacing.xl,
            "xxl 48dp" to spacing.xxl,
        ).forEach { (name, value) ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = name,
                    style = FoundryTheme.typography.label,
                    color = colors.onSurfaceMuted,
                    modifier = Modifier.width(96.dp),
                )
                Box(
                    modifier = Modifier
                        .width(value * 4)
                        .height(spacing.sm)
                        .background(color = colors.accent, shape = FoundryTheme.shapes.sm),
                )
            }
        }
    }
}

@Composable
private fun ShapesSection() {
    val colors = FoundryTheme.colors
    val spacing = FoundryTheme.spacing
    Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
        SectionTitle(text = "Shapes")
        Row(horizontalArrangement = Arrangement.spacedBy(spacing.md)) {
            listOf(
                "sm" to FoundryTheme.shapes.sm,
                "md" to FoundryTheme.shapes.md,
                "lg" to FoundryTheme.shapes.lg,
                "full" to FoundryTheme.shapes.full,
            ).forEach { (name, shape) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(spacing.xxl)
                            .background(color = colors.surface, shape = shape)
                            .border(width = Dp.Hairline, color = colors.outline, shape = shape),
                    )
                    Text(
                        text = name,
                        style = FoundryTheme.typography.caption,
                        color = colors.onSurfaceMuted,
                    )
                }
            }
        }
    }
}
