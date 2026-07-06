package io.github.marioponceg.foundry.tokens

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/** Contained radii — forged panel, not bubble. [full] is the pill/circular shape. */
@Immutable
public class FoundryShapes(
    public val sm: Shape,
    public val md: Shape,
    public val lg: Shape,
    public val full: Shape,
) {
    public companion object {
        public fun default(): FoundryShapes = FoundryShapes(
            sm = RoundedCornerShape(4.dp),
            md = RoundedCornerShape(8.dp),
            lg = RoundedCornerShape(12.dp),
            full = CircleShape,
        )
    }
}
