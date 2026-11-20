# Prompt before attempting real URL threat intelligence

```text
DO NOT IMPLEMENT A PROVIDER YET. Read AGENTS.md, docs/04-VERIFICATION-ENGINE.md,
docs/05-THREAT-MODEL.md, docs/06-PRIVACY.md, docs/07-API-AND-DATA.md
and contracts/verification.openapi.yaml.

Give a decision memo with:
1. Commercial vs noncommercial usage and provider licensing.
2. Exactly what leaves the device, consent flow and processing regions.
3. Backend request validation, authentication, throttling, quota and cache TTL.
4. SSRF controls with NO untrusted page fetch or automatic redirect follow.
5. Mapping of match/no-match/error/stale/partial into the four verdicts.
6. Source attribution, timestamp and user-facing uncertainty.
7. An adversarial test plan and implementation cost.

Do not add keys, endpoints, network code or provider SDKs until the product
owner selects a provider and approves these boundaries.
```
