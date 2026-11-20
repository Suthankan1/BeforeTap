# Implementation status

Updated: 2026-11-20

## Confirmed

- Two separate native-design Figma files: 20 screens each, OS-specific design treatments, clickable prototype links.
- Prompt-first repository structure, docs, safe fictional fixtures, native source shell and proposed API contract packaged.
- Historical LaTeX/PDF product design specification included in `reference/`.

## Not implemented or verified

- No real verification backend or threat-feed integration.
- No screenshot OCR, QR scanner, share extension, history persistence, accessibility audit or dark-mode implementation.
- No app-store submission, telemetry or production infrastructure.
- This source starter has not been compiled with Xcode or the Android SDK in this environment.

## Next exact slice

**M0.1 environment baseline:** open `apps/ios` on a Mac with XcodeGen/Xcode and `apps/android` in Android Studio; generate missing tool wrappers, build each demo shell, capture actual errors, fix only bootstrap issues, and record passing commands here. Then implement M1 domain model tests before any live verification.

## Session log template

- Date / tool / prompt:
- Scope and files changed:
- Commands/tests and results:
- Screenshot/accessibility review:
- Security/privacy implications:
- Decision needed:
- Next slice:
