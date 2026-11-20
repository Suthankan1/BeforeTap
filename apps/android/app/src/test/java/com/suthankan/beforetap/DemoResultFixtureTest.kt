package com.suthankan.beforetap

import com.suthankan.beforetap.domain.DemoResultFixture
import com.suthankan.beforetap.domain.Verdict
import java.time.Instant
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class DemoResultFixtureTest {
    @Test fun decodesAllFourSharedFixtures() {
        val expected = mapOf(
            "known-malicious" to Verdict.KNOWN_MALICIOUS,
            "suspicious" to Verdict.SUSPICIOUS,
            "no-known-threats" to Verdict.NO_KNOWN_THREATS,
            "unable-to-verify" to Verdict.UNABLE_TO_VERIFY
        )

        for ((filename, verdict) in expected) {
            val result = DemoResultFixture.decode(fixture(filename))
            assertEquals(verdict, result.verdict)
            assertEquals(Instant.parse("2026-11-20T00:00:00Z"), result.checkedAt)
            assertTrue(result.displayHost.endsWith(".example"))
            assertTrue(result.limitations.isNotEmpty())
            assertTrue(result.checks.isNotEmpty())
            if (verdict == Verdict.UNABLE_TO_VERIFY) {
                assertEquals("Provider timeout", result.checks.first().reason)
            }
            if (verdict == Verdict.KNOWN_MALICIOUS) {
                assertEquals("Fictional fixture", result.evidence.first().source)
            }
        }
    }

    @Test fun unknownVerdictFallsBackToUnableToVerify() {
        val fields = JSONObject(fixture("suspicious")).put("verdict", "FUTURE_VERDICT")
        assertEquals(Verdict.UNABLE_TO_VERIFY, DemoResultFixture.decode(fields.toString()).verdict)
    }

    @Test fun rejectsNonDemoMarker() {
        val fields = JSONObject(fixture("suspicious")).put("fixture", false)
        assertThrows(IllegalArgumentException::class.java) {
            DemoResultFixture.decode(fields.toString())
        }
    }

    private fun fixture(name: String): String =
        checkNotNull(javaClass.getResource("/$name.demo.json")).readText()
}
