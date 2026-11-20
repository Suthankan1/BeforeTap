# Threat model (initial, must be reviewed)

## Assets

Private messages, screenshots, scanned QR payloads, URL query tokens, optional on-device history, provider keys, inference/result integrity and user trust.

## Trust boundaries

1. Untrusted message/QR/screenshot → local parsing and display.
2. Local app → optional API gateway only after user consent.
3. Gateway → external licensed reputation provider.
4. Gateway → logs, cache, rate limiter and operational telemetry.
5. User → external website only through a deliberate user action.

## Threats and mitigations

| Threat | Mandatory control / validation |
|---|---|
| SSRF via malicious URL | No arbitrary backend fetch or redirect follow. If ever needed, separate architecture and dedicated SSRF review with DNS/IP rebinding defenses. |
| Unicode/IDN deception | Unicode + punycode explanation; public suffix library and confusable checks; do not normalize away evidence. |
| Open-redirect/URL wrapper | Distinguish entered URL from final destination; never label uninspected redirects as checked. |
| API key extraction | Secret only in server; mobile client never includes vendor key. |
| Sensitive screenshot exfiltration | On-device OCR, user-selected image, no upload by default; no analytics capture. |
| Query token leakage | Redact logs, crash reports and telemetry; controlled retention and deletion. |
| Result tampering/replay | TLS, authenticated endpoint, timestamps/TTL and schema validation; do not display stale verdict as new. |
| Rate-limit abuse/cost spike | Per-client limits, quota, caching, bounded retries; no anonymous unbounded provider relay. |
| False-safe language | Four verdict semantics and explicit uncertainty; content review and usability testing. |
| Device compromise | Limit stored secrets; treat device security as a residual risk, no unsupported guarantees. |
| Poisoned threat source | Provider provenance, validation, freshness and fallback state. |

## Security review gates

- Tests demonstrate *no network* from local parsing, OCR extraction and QR decode.
- A failed lookup cannot be represented as `NO_KNOWN_THREATS`.
- External URL launch requires separate user confirmation.
- Consent and data-sharing copy reviewed on each platform.
- Privacy and security reviewer signs off before beta; penetration test before public release.

Reference: OWASP MASVS (verify current requirements at https://mas.owasp.org/).
