package com.suthankan.beforetap.domain

import java.time.Instant
import org.json.JSONArray
import org.json.JSONObject

data class DemoResultFixture(
    val displayHost: String,
    val verdict: Verdict,
    val checkedAt: Instant,
    val evidence: List<Evidence>,
    val checks: List<Check>,
    val limitations: List<String>
) {
    data class Evidence(
        val code: String,
        val severity: String,
        val explanation: String,
        val source: String
    )

    data class Check(val name: String, val state: String, val reason: String?)

    companion object {
        fun decode(text: String): DemoResultFixture {
            val fields = JSONObject(text)
            require(fields.get("fixture") == true) { "Expected a demo fixture." }
            val verdictCode = fields.requireString("verdict")
            return DemoResultFixture(
                displayHost = fields.requireString("displayHost"),
                verdict = Verdict.entries.firstOrNull { it.name == verdictCode }
                    ?: Verdict.UNABLE_TO_VERIFY,
                checkedAt = Instant.parse(fields.requireString("checkedAt")),
                evidence = fields.getJSONArray("evidence").mapValues { array, index ->
                    val evidence = array.getJSONObject(index)
                    Evidence(
                        code = evidence.requireString("code"),
                        severity = evidence.requireString("severity"),
                        explanation = evidence.requireString("explanation"),
                        source = evidence.requireString("source")
                    )
                },
                checks = fields.getJSONArray("checks").mapValues { array, index ->
                    val check = array.getJSONObject(index)
                    Check(
                        name = check.requireString("name"),
                        state = check.requireString("state"),
                        reason = if (check.isNull("reason")) null else check.requireString("reason")
                    )
                },
                limitations = fields.getJSONArray("limitations").mapValues { array, index ->
                    array.get(index) as? String ?: error("Expected a string limitation.")
                }
            )
        }
    }
}

private fun JSONObject.requireString(key: String): String =
    get(key) as? String ?: throw IllegalArgumentException("Expected a string for $key.")

private fun <T> JSONArray.mapValues(read: (JSONArray, Int) -> T): List<T> =
    List(length()) { index -> read(this, index) }
