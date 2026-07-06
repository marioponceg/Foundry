package io.github.marioponceg.foundry.tokens

import kotlin.test.Test
import kotlin.test.assertEquals

class M3MappingTest {

    @Test
    fun `color scheme mirrors foundry tokens in both modes`() {
        listOf(true to FoundryColors.dark(), false to FoundryColors.light()).forEach { (dark, c) ->
            val scheme = foundryColorScheme(colors = c, darkTheme = dark)
            assertEquals(c.accent, scheme.primary)
            assertEquals(c.onAccent, scheme.onPrimary)
            assertEquals(c.background, scheme.background)
            assertEquals(c.onBackground, scheme.onBackground)
            assertEquals(c.surface, scheme.surface)
            assertEquals(c.onSurface, scheme.onSurface)
            assertEquals(c.onSurfaceMuted, scheme.onSurfaceVariant)
            assertEquals(c.danger, scheme.error)
            assertEquals(c.onDanger, scheme.onError)
            assertEquals(c.outline, scheme.outline)
        }
    }

    @Test
    fun `m3 typography mirrors foundry roles`() {
        val t = FoundryTypography.default()
        val m3 = foundryM3Typography(t)
        assertEquals(t.display, m3.displaySmall)
        assertEquals(t.title, m3.titleLarge)
        assertEquals(t.heading, m3.titleMedium)
        assertEquals(t.body, m3.bodyLarge)
        assertEquals(t.bodyStrong, m3.bodyMedium)
        assertEquals(t.label, m3.labelLarge)
        assertEquals(t.caption, m3.bodySmall)
    }

    @Test
    fun `m3 shapes mirror foundry shapes`() {
        val s = FoundryShapes.default()
        val m3 = foundryM3Shapes(s)
        assertEquals(s.sm, m3.small)
        assertEquals(s.md, m3.medium)
        assertEquals(s.lg, m3.large)
    }
}
