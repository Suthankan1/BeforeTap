# Architecture Decision Log

Only decisions accepted by the product owner belong here. Revisit rather than silently override.

## ADR-001 — Two native clients

- **Status:** Accepted
- **Decision:** SwiftUI on iOS; Kotlin + Jetpack Compose Material 3 on Android.
- **Reason:** Best OS-native UI, accessibility, performance and platform integrations.
- **Consequence:** Duplicate UI implementation; share semantic contracts and fixtures.

## ADR-002 — Four evidence-aware verdicts

- **Status:** Accepted
- **Decision:** Known malicious, suspicious, no known threats, unable to verify. Display checked signals, unverified signals, source and time.
- **Reason:** A clean reputation lookup must not imply safety; network failures must not silently pass.

## ADR-003 — Local-first input handling

- **Status:** Proposed / review before implementation
- **Decision:** Parse text, QR and screenshots locally where possible; optional remote lookup after explicit informed consent.
- **Open questions:** Provider contract, regional data handling, retention defaults, analytics policy.

## ADR-004 — Backend placement

- **Status:** Proposed
- **Decision:** Separate server-side verification gateway for commercial providers and policy, never ship provider credentials in clients.
- **Open questions:** Hosting, provider, cost, rate limits, abuse handling and jurisdiction.

## ADR-005 — Client persistence

- **Status:** Pending
- **Options:** On-device encrypted history with deletion; opt-in sync in later release.
- **Do not implement without a privacy decision.**
