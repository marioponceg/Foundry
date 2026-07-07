package io.github.marioponceg.foundry.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.marioponceg.foundry.tokens.FoundryColors
import io.github.marioponceg.foundry.tokens.FoundryTheme

/**
 * A Foundry text input. Wraps Material 3 [OutlinedTextField] behind Foundry's API: a labeled,
 * bordered field resolving every color from [FoundryTheme] tokens. The border/label/cursor move to
 * `accent` on focus and to `danger` when [isError]; [supportingText] is the single helper-or-error
 * line beneath the field. [label], [placeholder] and [supportingText] map to their M3 slots only
 * when non-null.
 */
@Composable
public fun FoundryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        textStyle = FoundryTheme.typography.body,
        // M3 Text (NOT FoundryText) in the slots: M3 injects the state-driven color (accent on
        // focus, danger on error) via `colors`; FoundryText would force onBackground and break it.
        label = label?.let { { Text(text = it) } },
        placeholder = placeholder?.let { { Text(text = it) } },
        supportingText = supportingText?.let { { Text(text = it) } },
        isError = isError,
        singleLine = singleLine,
        shape = FoundryTheme.shapes.md,
        colors = foundryTextFieldColors(FoundryTheme.colors),
    )
}

/** Token-resolved colors for the outlined field, across focus/error/disabled states. */
@Composable
private fun foundryTextFieldColors(tokens: FoundryColors): TextFieldColors =
    OutlinedTextFieldDefaults.colors(
        focusedTextColor = tokens.onSurface,
        unfocusedTextColor = tokens.onSurface,
        disabledTextColor = tokens.onSurfaceMuted,
        errorTextColor = tokens.onSurface,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent,
        cursorColor = tokens.accent,
        errorCursorColor = tokens.danger,
        focusedBorderColor = tokens.accent,
        unfocusedBorderColor = tokens.outline,
        disabledBorderColor = tokens.outline,
        errorBorderColor = tokens.danger,
        focusedLabelColor = tokens.accent,
        unfocusedLabelColor = tokens.onSurfaceMuted,
        disabledLabelColor = tokens.onSurfaceMuted,
        errorLabelColor = tokens.danger,
        focusedPlaceholderColor = tokens.onSurfaceMuted,
        unfocusedPlaceholderColor = tokens.onSurfaceMuted,
        disabledPlaceholderColor = tokens.onSurfaceMuted,
        focusedSupportingTextColor = tokens.onSurfaceMuted,
        unfocusedSupportingTextColor = tokens.onSurfaceMuted,
        disabledSupportingTextColor = tokens.onSurfaceMuted,
        errorSupportingTextColor = tokens.danger,
    )
