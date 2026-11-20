# BeforeTap agent instructions (canonical)

Apply these instructions to **all** AI coding tools. Read `README.md`, `STATUS.md`, `DECISIONS.md`, `ROADMAP.md` and only the relevant `docs/` file before editing. The user is the decision-maker.

## Product invariants

1. Two independent **native** apps: SwiftUI in `apps/ios`, Kotlin/Compose in `apps/android`. Share *contracts and behavior*, not UI code.
2. Mirror the respective platform Figma file; preserve OS navigation, safe areas, typography, accessibility and motion.
3. Four result outcomes only: `KNOWN_MALICIOUS`, `SUSPICIOUS`, `NO_KNOWN_THREATS`, `UNABLE_TO_VERIFY`. Never collapse outage into "safe".
4. No automatic URL opening, link prefetching, active crawling, DNS resolution, redirect following or remote OCR without an approved threat model and explicit implementation decision.
5. Third-party lookups require permission, a documented provider, server-side keys, bounded timeouts and transparent source/time attribution.
6. User messages/screenshots may contain secrets. Minimize collection, avoid logs/analytics, enable deletion, and never upload raw images by default.
7. The included demo engine must stay visibly labeled `DEMO — NO LIVE SCAN` until a real verified integration exists.
8. No hidden tracking or unsupported claims of detection accuracy.

## Working agreement

- First summarize the exact vertical slice, affected files, acceptance criteria and tests. Ask when architecture/privacy choices are missing.
- Implement the smallest reviewable piece; avoid broad rewrites and unrequested dependencies.
- Add tests for parsers, status mapping, failures and security boundaries before claiming a slice done.
- Run available build/tests and report exact commands/results. If tooling is unavailable, state "not run".
- Update `STATUS.md` and relevant checklist. Add architectural decisions to `DECISIONS.md`.
- Preserve unrelated edits and never commit real API keys, personal screenshots, real suspicious URLs or credentials.

## Order of authority

User's explicit request → this AGENTS.md → accepted decisions → product/security docs → platform docs → individual prompts. Tool-specific instruction files only point here; they must not fork product requirements.

## Project gates

No production readiness claims until `docs/08-QA-RELEASE.md` gates pass. No production threat verdict without provider provenance, security review, tests and service observability.
