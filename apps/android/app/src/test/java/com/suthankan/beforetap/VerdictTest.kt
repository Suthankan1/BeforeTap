package com.suthankan.beforetap

import com.suthankan.beforetap.domain.DemoFixture
import com.suthankan.beforetap.domain.Verdict
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class VerdictTest {
    @Test fun supportedVerdictsMatchContract() {
        assertEquals(
            setOf("KNOWN_MALICIOUS", "SUSPICIOUS", "NO_KNOWN_THREATS", "UNABLE_TO_VERIFY"),
            Verdict.entries.map { it.name }.toSet()
        )
    }

    @Test fun demoIsExplicitlyNonLive() {
        assertEquals(Verdict.SUSPICIOUS, DemoFixture.suspicious.verdict)
        assertTrue(DemoFixture.suspicious.isDemo)
        assertTrue(DemoFixture.suspicious.displayHost.endsWith(".example"))
        assertTrue(DemoFixture.suspicious.limitations.any { it.contains("No live") })
    }
}
