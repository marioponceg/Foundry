package io.github.marioponceg.foundry.tokens

import androidx.compose.ui.graphics.Color
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.test.Test
import kotlin.test.assertTrue

class ContrastTest {

    @Test
    fun `on-colors reach AA contrast over their color in both schemes`() {
        listOf("dark" to FoundryColors.dark(), "light" to FoundryColors.light()).forEach { (scheme, c) ->
            listOf(
                "background" to (c.background to c.onBackground),
                "surface" to (c.surface to c.onSurface),
                "accent" to (c.accent to c.onAccent),
                "success" to (c.success to c.onSuccess),
                "warning" to (c.warning to c.onWarning),
                "danger" to (c.danger to c.onDanger),
            ).forEach { (role, pair) ->
                val ratio = contrastRatio(pair.first, pair.second)
                assertTrue(ratio >= 4.5, "$scheme/$role: $ratio < 4.5")
            }
        }
    }

    @Test
    fun `muted text reaches AA contrast over background and surface`() {
        listOf("dark" to FoundryColors.dark(), "light" to FoundryColors.light()).forEach { (scheme, c) ->
            listOf("background" to c.background, "surface" to c.surface).forEach { (base, baseColor) ->
                val ratio = contrastRatio(baseColor, c.onSurfaceMuted)
                assertTrue(ratio >= 4.5, "$scheme/onSurfaceMuted over $base: $ratio < 4.5")
            }
        }
    }

    @Test
    fun `rarity colors reach 3 to 1 over background and surface in both schemes`() {
        listOf("dark" to FoundryColors.dark(), "light" to FoundryColors.light()).forEach { (scheme, c) ->
            val rarities = listOf(
                "common" to c.rarity.common,
                "uncommon" to c.rarity.uncommon,
                "rare" to c.rarity.rare,
                "epic" to c.rarity.epic,
                "legendary" to c.rarity.legendary,
            )
            listOf("background" to c.background, "surface" to c.surface).forEach { (base, baseColor) ->
                rarities.forEach { (tier, tierColor) ->
                    val ratio = contrastRatio(baseColor, tierColor)
                    assertTrue(ratio >= 3.0, "$scheme/$tier over $base: $ratio < 3.0")
                }
            }
        }
    }

    private fun contrastRatio(a: Color, b: Color): Double {
        val la = a.relativeLuminance()
        val lb = b.relativeLuminance()
        return (max(la, lb) + 0.05) / (min(la, lb) + 0.05)
    }

    private fun Color.relativeLuminance(): Double =
        0.2126 * channel(red) + 0.7152 * channel(green) + 0.0722 * channel(blue)

    private fun channel(c: Float): Double {
        val v = c.toDouble()
        return if (v <= 0.04045) v / 12.92 else ((v + 0.055) / 1.055).pow(2.4)
    }
}
