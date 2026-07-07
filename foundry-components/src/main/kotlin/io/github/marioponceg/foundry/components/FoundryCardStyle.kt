package io.github.marioponceg.foundry.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import io.github.marioponceg.foundry.tokens.FoundryColors
import io.github.marioponceg.foundry.tokens.FoundryTheme

/** The two Foundry card variants, selected as a typed variant. */
public enum class FoundryCardStyle { Filled, Outlined }

/**
 * A Foundry card: a flat "forged panel" container wrapping the Material 3 card family behind
 * Foundry's API. [style] picks Filled ([Card]) or Outlined ([OutlinedCard]); both render flat
 * (no elevation) on `surface` with `shapes.lg`. When [onClick] is non-null the whole card is
 * clickable (ripple); otherwise it is static. [content] is a free container slot padded by
 * [contentPadding] — defaults to `spacing.md`; pass `PaddingValues(0.dp)` for edge-to-edge media.
 */
@Composable
public fun FoundryCard(
    modifier: Modifier = Modifier,
    style: FoundryCardStyle = FoundryCardStyle.Filled,
    onClick: (() -> Unit)? = null,
    contentPadding: PaddingValues? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = FoundryTheme.colors
    val shape = FoundryTheme.shapes.lg
    val padding = contentPadding ?: PaddingValues(FoundryTheme.spacing.md)
    // Wrap the slot once so every branch shares the padded body; the M3 card's own ColumnScope
    // is unused — the inner Column provides the padding and the scope the content sees.
    val body: @Composable ColumnScope.() -> Unit = {
        Column(modifier = Modifier.padding(padding), content = content)
    }
    when (style) {
        FoundryCardStyle.Filled -> FilledFoundryCard(
            modifier = modifier,
            shape = shape,
            colors = colors,
            onClick = onClick,
            body = body,
        )
        FoundryCardStyle.Outlined -> OutlinedFoundryCard(
            modifier = modifier,
            shape = shape,
            colors = colors,
            onClick = onClick,
            body = body,
        )
    }
}

/** [FoundryCardStyle.Filled] branch: flat, `surface`-colored, clickable iff [onClick] is non-null. */
@Composable
private fun FilledFoundryCard(
    modifier: Modifier,
    shape: Shape,
    colors: FoundryColors,
    onClick: (() -> Unit)?,
    body: @Composable ColumnScope.() -> Unit,
) {
    val cardColors = CardDefaults.cardColors(
        containerColor = colors.surface,
        contentColor = colors.onSurface,
    )
    val elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            shape = shape,
            colors = cardColors,
            elevation = elevation,
            content = body,
        )
    } else {
        Card(
            modifier = modifier,
            shape = shape,
            colors = cardColors,
            elevation = elevation,
            content = body,
        )
    }
}

/** [FoundryCardStyle.Outlined] branch: `outline`-bordered, clickable iff [onClick] is non-null. */
@Composable
private fun OutlinedFoundryCard(
    modifier: Modifier,
    shape: Shape,
    colors: FoundryColors,
    onClick: (() -> Unit)?,
    body: @Composable ColumnScope.() -> Unit,
) {
    val cardColors = CardDefaults.outlinedCardColors(
        containerColor = colors.surface,
        contentColor = colors.onSurface,
    )
    val border = BorderStroke(1.dp, colors.outline)
    if (onClick != null) {
        OutlinedCard(
            onClick = onClick,
            modifier = modifier,
            shape = shape,
            colors = cardColors,
            border = border,
            content = body,
        )
    } else {
        OutlinedCard(
            modifier = modifier,
            shape = shape,
            colors = cardColors,
            border = border,
            content = body,
        )
    }
}
