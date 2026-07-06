package io.github.marioponceg.foundry.tokens

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TokenScalesTest {

    @Test
    fun `spacing follows the 4dp scale`() {
        val s = FoundrySpacing.default()
        assertEquals(listOf(4.dp, 8.dp, 16.dp, 24.dp, 32.dp, 48.dp), listOf(s.xs, s.sm, s.md, s.lg, s.xl, s.xxl))
    }

    @Test
    fun `type scale sizes descend from display to caption`() {
        val t = FoundryTypography.default()
        assertEquals(36.sp, t.display.fontSize)
        assertEquals(24.sp, t.title.fontSize)
        assertEquals(18.sp, t.heading.fontSize)
        assertEquals(16.sp, t.body.fontSize)
        assertEquals(16.sp, t.bodyStrong.fontSize)
        assertEquals(14.sp, t.label.fontSize)
        assertEquals(12.sp, t.caption.fontSize)
    }

    @Test
    fun `display roles share the Cinzel family and body roles do not`() {
        val t = FoundryTypography.default()
        assertEquals(t.display.fontFamily, t.title.fontFamily)
        assertEquals(t.display.fontFamily, t.heading.fontFamily)
        assertTrue(t.body.fontFamily != t.display.fontFamily, "body must not use the display family")
    }
}
