package com.suthankan.beforetap.domain

enum class Verdict {
    KNOWN_MALICIOUS,
    SUSPICIOUS,
    NO_KNOWN_THREATS,
    UNABLE_TO_VERIFY
}

data class Evidence(
    val id: String,
    val title: String,
    val explanation: String,
    val source: String
)

data class VerificationResult(
    val displayHost: String,
    val verdict: Verdict,
    val evidence: List<Evidence>,
    val limitations: List<String>,
    val isDemo: Boolean
)

object DemoFixture {
    val suspicious = VerificationResult(
        displayHost = "verify-login.example",
        verdict = Verdict.SUSPICIOUS,
        evidence = listOf(
            Evidence(
                id = "unfamiliar-domain",
                title = "Unfamiliar domain",
                explanation = "The visible brand and destination are not the same.",
                source = "Illustrative local signal"
            )
        ),
        limitations = listOf("No live threat-intelligence lookup was performed."),
        isDemo = true
    )
}
