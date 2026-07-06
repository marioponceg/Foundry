package io.github.marioponceg.foundry.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import io.github.marioponceg.foundry.tokens.FoundryTheme

/** The seven Foundry typography roles, selected as a typed variant. */
public enum class FoundryTextStyle {
    Display, Title, Heading, Body, BodyStrong, Label, Caption
}

/**
 * Text rendered with a Foundry typography role. Wraps Material 3 [Text] behind Foundry's API.
 *
 * [color] defaults to inheriting the surface's on-color: when left [Color.Unspecified] it
 * resolves to [FoundryTheme.colors] `.onBackground`, so text is legible on Foundry surfaces
 * without ceremony. Pass an explicit color (e.g. `FoundryTheme.colors.accent` or a rarity)
 * to override.
 */
@Composable
public fun FoundryText(
    text: String,
    modifier: Modifier = Modifier,
    style: FoundryTextStyle = FoundryTextStyle.Body,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
) {
    val typography = FoundryTheme.typography
    val resolvedStyle: TextStyle = when (style) {
        FoundryTextStyle.Display -> typography.display
        FoundryTextStyle.Title -> typography.title
        FoundryTextStyle.Heading -> typography.heading
        FoundryTextStyle.Body -> typography.body
        FoundryTextStyle.BodyStrong -> typography.bodyStrong
        FoundryTextStyle.Label -> typography.label
        FoundryTextStyle.Caption -> typography.caption
    }
    Text(
        text = text,
        modifier = modifier,
        color = color.takeOrElse { FoundryTheme.colors.onBackground },
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign ?: TextAlign.Unspecified,
        style = resolvedStyle,
    )
}
