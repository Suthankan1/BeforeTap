package com.suthankan.beforetap

import com.suthankan.beforetap.domain.LocalWebLink
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalWebLinkTest {
    @Test fun parsesSharedWebLinkCases() {
        val cases = checkNotNull(javaClass.getResource("/web-links.txt")).readText()
        cases.lineSequence().forEachIndexed { index, line ->
            if (line.isBlank() || line.startsWith('#')) return@forEachIndexed
            val fields = line.split('|')
            val expected = if (fields[1].isEmpty()) null else LocalWebLink(
                scheme = fields[1],
                host = fields[2],
                port = fields[3].toIntOrNull(),
                hasUserInfo = fields[4].toBooleanStrict()
            )
            assertEquals("Case ${index + 1}", expected, LocalWebLink.parse(fields[0]))
        }
    }
}
