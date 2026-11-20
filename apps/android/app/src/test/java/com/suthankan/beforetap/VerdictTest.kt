package com.suthankan.beforetap

import com.suthankan.beforetap.domain.DemoFixture
import com.suthankan.beforetap.domain.Verdict
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class VerdictTest {
    @Test fun demoIsExplicitlyNonLive() {
        assertEquals(Verdict.SUSPICIOUS, DemoFixture.suspicious.verdict)
        assertTrue(DemoFixture.suspicious.isDemo)
        assertTrue(DemoFixture.suspicious.limitations.any { it.contains("No live") })
    }
}
