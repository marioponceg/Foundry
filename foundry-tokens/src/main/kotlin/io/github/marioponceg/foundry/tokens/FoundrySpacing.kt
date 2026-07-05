package io.github.marioponceg.foundry.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** 4dp-based spacing scale. Six deliberate steps — new steps earn their place, they are not added casually. */
@Immutable
public class FoundrySpacing(
    public val xs: Dp,
    public val sm: Dp,
    public val md: Dp,
    public val lg: Dp,
    public val xl: Dp,
    public val xxl: Dp,
) {
    public companion object {
        public fun default(): FoundrySpacing = FoundrySpacing(
            xs = 4.dp,
            sm = 8.dp,
            md = 16.dp,
            lg = 24.dp,
            xl = 32.dp,
            xxl = 48.dp,
        )
    }
}
