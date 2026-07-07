package io.github.marioponceg.foundry.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.marioponceg.foundry.components.FoundryTextField
import io.github.marioponceg.foundry.tokens.FoundryTheme

@Composable
internal fun TextFieldScreen() {
    val spacing = FoundryTheme.spacing
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.md),
    ) {
        FoundryTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            label = "Your name",
            placeholder = "e.g. Mario",
            supportingText = "This one is live — type in it",
        )
        FoundryTextField(
            value = "bad@",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = "Email",
            supportingText = "Invalid email address",
            isError = true,
        )
        FoundryTextField(
            value = "Cannot edit",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = "Disabled",
            enabled = false,
        )
        FoundryTextField(
            value = "A longer note that wraps across multiple lines.",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = "Notes",
            singleLine = false,
        )
    }
}
