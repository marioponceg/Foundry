package io.github.marioponceg.foundry.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Two families, seven roles. Display roles use Cinzel (bundled, OFL — the engraved
 * fantasy voice); body roles stay on the system sans for density and legibility.
 */
@Immutable
public class FoundryTypography(
    public val display: TextStyle,
    public val title: TextStyle,
    public val heading: TextStyle,
    public val body: TextStyle,
    public val bodyStrong: TextStyle,
    public val label: TextStyle,
    public val caption: TextStyle,
) {
    public companion object {
        @OptIn(ExperimentalTextApi::class)
        public fun default(): FoundryTypography {
            val cinzel = FontFamily(
                Font(
                    resId = R.font.cinzel,
                    weight = FontWeight.Medium,
                    variationSettings = FontVariation.Settings(FontVariation.weight(500)),
                ),
            )
            return FoundryTypography(
                display = TextStyle(
                    fontFamily = cinzel,
                    fontWeight = FontWeight.Medium,
                    fontSize = 36.sp,
                    lineHeight = 44.sp,
                ),
                title = TextStyle(
                    fontFamily = cinzel,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                ),
                heading = TextStyle(
                    fontFamily = cinzel,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    lineHeight = 26.sp,
                ),
                body = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                ),
                bodyStrong = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                ),
                label = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.5.sp,
                ),
                caption = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                ),
            )
        }
    }
}
