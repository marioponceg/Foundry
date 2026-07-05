package io.github.marioponceg.foundry.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Rarity tiers — the cross-game color convention (common → legendary), designed to be used
 * as text/border colors over Foundry surfaces. Game-specific tiers or exact franchise hexes
 * belong in the consuming app, composed via a custom [RarityColors] instance.
 */
@Immutable
public class RarityColors(
    public val common: Color,
    public val uncommon: Color,
    public val rare: Color,
    public val epic: Color,
    public val legendary: Color,
)

/**
 * The Foundry color vocabulary. Semantic roles, not literal hues: [accent] is the single
 * protagonist color; state roles carry their own on-colors; [rarity] is the gaming extension.
 *
 * Retheming = constructing a new instance (composition, not inheritance). The defaults are
 * the "Forja" language: molten gold on obsidian (dark) and parchment (light). Every on-color
 * pair holds WCAG AA (>= 4.5:1) and every rarity holds >= 3:1 over background and surface —
 * enforced by unit tests.
 */
@Immutable
@Suppress("LongParameterList")
public class FoundryColors(
    public val background: Color,
    public val onBackground: Color,
    public val surface: Color,
    public val onSurface: Color,
    public val onSurfaceMuted: Color,
    public val outline: Color,
    public val accent: Color,
    public val onAccent: Color,
    public val success: Color,
    public val onSuccess: Color,
    public val warning: Color,
    public val onWarning: Color,
    public val danger: Color,
    public val onDanger: Color,
    public val rarity: RarityColors,
) {
    public companion object {
        /** Forja dark: molten gold on obsidian. */
        public fun dark(): FoundryColors = FoundryColors(
            background = Color(0xFF0E1013),
            onBackground = Color(0xFFECE5D3),
            surface = Color(0xFF1A1D23),
            onSurface = Color(0xFFECE5D3),
            onSurfaceMuted = Color(0xFF9A917B),
            outline = Color(0xFF3A3E48),
            accent = Color(0xFFE8A33D),
            onAccent = Color(0xFF241A05),
            success = Color(0xFF6FA857),
            onSuccess = Color(0xFF0F2408),
            warning = Color(0xFFD9C24E),
            onWarning = Color(0xFF2A2206),
            danger = Color(0xFFE25B4A),
            onDanger = Color(0xFF2B0C08),
            rarity = RarityColors(
                common = Color(0xFFA8A29A),
                uncommon = Color(0xFF4FBF4F),
                rare = Color(0xFF4A9EE8),
                epic = Color(0xFFB048E8),
                legendary = Color(0xFFE8762B),
            ),
        )

        /** Forja light: ink on parchment. */
        public fun light(): FoundryColors = FoundryColors(
            background = Color(0xFFF4EDDE),
            onBackground = Color(0xFF2A2418),
            surface = Color(0xFFFBF7EC),
            onSurface = Color(0xFF2A2418),
            onSurfaceMuted = Color(0xFF5C5340),
            outline = Color(0xFFD6CCB4),
            accent = Color(0xFFA2671B),
            onAccent = Color(0xFFFFFFFF),
            success = Color(0xFF4A7A34),
            onSuccess = Color(0xFFFFFFFF),
            warning = Color(0xFF8A6D1C),
            onWarning = Color(0xFFFFFFFF),
            danger = Color(0xFFA83228),
            onDanger = Color(0xFFFFFFFF),
            rarity = RarityColors(
                common = Color(0xFF6E675C),
                uncommon = Color(0xFF2E8B2E),
                rare = Color(0xFF1F6BB5),
                epic = Color(0xFF7E2FB0),
                legendary = Color(0xFFC05A10),
            ),
        )
    }
}
