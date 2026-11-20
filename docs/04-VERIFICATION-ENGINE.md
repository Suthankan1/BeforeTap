# Verification semantics — security-critical

This is the core safety contract. The demo data is **not a security service**.

## Pipeline

1. **Input capture:** User pastes text/URL, chooses a screenshot or scans QR locally.
2. **Extraction:** On-device OCR/QR decode only. Show extracted content for user review.
3. **Parsing:** Extract candidate URLs without navigating; reject unsupported schemes and invalid forms. Preserve the raw input for display within a controlled local retention policy.
4. **Local indicators:** Display host, scheme, IP-literal, IDN/punycode, lookalike characters, unusual structure and misleading visible text. These are *signals*, not proof of maliciousness.
5. **Optional reputation lookup:** Only after consent and licensing; send a minimal normalized URL to a server-side gateway using the published contract.
6. **Evidence synthesis:** Preserve provider match, local indicators, failures, source and freshness.
7. **Verdict:** Explicitly report what was checked and what was not.

## Exact verdicts

| Verdict | Minimum condition | Required wording |
|---|---|---|
| `KNOWN_MALICIOUS` | Fresh confirmed matching threat-intelligence evidence with provenance | "Known malicious" and source/time |
| `SUSPICIOUS` | One or more explainable warning signals without confirmed malicious match | "Suspicious — verify independently" |
| `NO_KNOWN_THREATS` | Required lookup completed and returned no known match; limitations shown | "No known threats found — not proof of safety" |
| `UNABLE_TO_VERIFY` | Required check failed, was unavailable, stale or not consented to | "Unable to verify fully" |

If local suspicious signs coexist with provider outage, show the warning indicators **and** incomplete-check explanation; never convert to a clean result.

## Parsing/canonicalization caveats

- Reject `javascript:`, `file:`, `data:`, `intent:` and unsupported custom schemes for URL verification; never execute them.
- HTTP and HTTPS only for initial web links. Normalize carefully without changing semantics of encoded paths, host names or Unicode.
- Treat user-info `https://brand.example@host.example/` as host.example.
- Display the effective registrable domain through a maintained public suffix list; naive "last two labels" is wrong for many TLDs.
- Internationalized domains and Unicode confusables require clear Unicode/ASCII presentation and cautious warnings.
- Trailing dots, default/nondefault ports, percent encoding, IPv6 literals, mixed case, fragments and whitespace need tests.
- Do not automatically resolve redirects or open URL previews just to inspect them.
- Avoid logging raw input strings, SMS text, OCR output or full query strings.

## Provider integration

Choose provider **after** licensing, privacy and cost review. Google Safe Browsing is for non-commercial use; Google's Web Risk is a commercial option. Do not quietly use an API under incompatible terms.

Required server properties: secret manager, timeout, retries with bounds, provider quota handling, cache freshness, user rate limits, security monitoring, no blind redirect fetching, no SSRF, no open proxy, and audit logs with sensitive data minimized.

## Honest absence of evidence

Threat feeds have coverage gaps and delays. Local heuristics cause false positives and false negatives. No provider match never means absolute safety. Show timestamps, sources and limitations; support an independent check via official app/site.

## Test matrix

- Known threat fixture with signed/attributed provider match.
- Benign-looking local sample with suspicious Unicode/userinfo.
- Clean lookup response with explicit caveat.
- Offline, timeout, 429, 5xx, invalid response, stale cache, user denies consent.
- Multiple URLs in one message and invalid QR/screenshot contents.
- URL extraction never triggers network requests.
- Query strings containing tokens never enter logs or analytics.
