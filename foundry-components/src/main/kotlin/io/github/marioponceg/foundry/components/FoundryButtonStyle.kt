package io.github.marioponceg.foundry.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.marioponceg.foundry.tokens.FoundryColors
import io.github.marioponceg.foundry.tokens.FoundrySpacing
import io.github.marioponceg.foundry.tokens.FoundryTheme

/** Diameter of the loading spinner — sized to sit within the label's line box. */
private val ButtonSpinnerSize = 18.dp

/** The four Foundry button variants, selected as a typed variant. */
public enum class FoundryButtonStyle { Primary, Secondary, Tertiary, Destructive }

/**
 * A Foundry button. Wraps the Material 3 button family behind Foundry's API: [style] picks the
 * emphasis/intent and every default (colors, border, shape, elevation, padding) is resolved from
 * [FoundryTheme] tokens.
 *
 * - `Primary` / `Destructive` are filled buttons (accent / danger palette).
 * - `Secondary` is outlined (accent content, `outline` border).
 * - `Tertiary` is text-only (accent content).
 *
 * [loading] swaps the content for a spinner and suppresses clicks while keeping the button's
 * active colors; [enabled] drives the token-resolved disabled treatment. [leadingIcon] is an
 * optional slot rendered before the label (hidden while loading); its content inherits the
 * button's content color via `LocalContentColor`.
 */
@Composable
public fun FoundryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: FoundryButtonStyle = FoundryButtonStyle.Primary,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    val colors = FoundryTheme.colors
    val spacing = FoundryTheme.spacing
    val shape = FoundryTheme.shapes.md
    val contentPadding = PaddingValues(horizontal = spacing.md, vertical = spacing.sm)
    // Keep active colors during loading (don't force enabled=false, which would grey it out) —
    // just gate the click. Effective clickability is `enabled && !loading`.
    val gatedOnClick: () -> Unit = { if (!loading) onClick() }
    val content: @Composable RowScope.() -> Unit = {
        FoundryButtonContent(text = text, loading = loading, leadingIcon = leadingIcon, spacing = spacing)
    }
    when (style) {
        FoundryButtonStyle.Primary -> Button(
            onClick = gatedOnClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            colors = filledButtonColors(container = colors.accent, content = colors.onAccent, tokens = colors),
            elevation = null,
            contentPadding = contentPadding,
            content = content,
        )
        FoundryButtonStyle.Destructive -> Button(
            onClick = gatedOnClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            colors = filledButtonColors(container = colors.danger, content = colors.onDanger, tokens = colors),
            elevation = null,
            contentPadding = contentPadding,
            content = content,
        )
        FoundryButtonStyle.Secondary -> OutlinedButton(
            onClick = gatedOnClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = colors.accent,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = colors.onSurfaceMuted,
            ),
            border = BorderStroke(1.dp, colors.outline),
            contentPadding = contentPadding,
            content = content,
        )
        FoundryButtonStyle.Tertiary -> TextButton(
            onClick = gatedOnClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            colors = ButtonDefaults.textButtonColors(
                contentColor = colors.accent,
                disabledContentColor = colors.onSurfaceMuted,
            ),
            contentPadding = contentPadding,
            content = content,
        )
    }
}

/** Filled-button colors with token-resolved disabled treatment (muted, not M3's default alpha). */
@Composable
private fun filledButtonColors(container: Color, content: Color, tokens: FoundryColors): ButtonColors =
    ButtonDefaults.buttonColors(
        containerColor = container,
        contentColor = content,
        disabledContainerColor = tokens.outline,
        disabledContentColor = tokens.onSurfaceMuted,
    )

/**
 * Button content: the spinner while [loading], otherwise the optional [leadingIcon] + label.
 * Uses M3 [Text] (NOT FoundryText) so the label inherits the button's content color via
 * `LocalContentColor`; FoundryText would force `onBackground` and break color inheritance.
 */
@Composable
private fun FoundryButtonContent(
    text: String,
    loading: Boolean,
    leadingIcon: (@Composable () -> Unit)?,
    spacing: FoundrySpacing,
) {
    if (loading) {
        CircularProgressIndicator(
            modifier = Modifier.size(ButtonSpinnerSize),
            color = LocalContentColor.current,
            strokeWidth = 2.dp,
        )
    } else {
        if (leadingIcon != null) {
            leadingIcon()
            Spacer(modifier = Modifier.width(spacing.sm))
        }
        Text(text = text, style = FoundryTheme.typography.label)
    }
}
