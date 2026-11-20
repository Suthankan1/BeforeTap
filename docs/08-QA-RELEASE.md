# QA, accessibility and release gate

## Per-slice definition of done

- Source code and tests reviewed.
- Happy, empty, error, denied, offline and cancellation states considered.
- Screenshots compared with correct OS Figma screen.
- VoiceOver/TalkBack, font scaling and high contrast evaluated.
- No unverified live detection or security claim.
- `STATUS.md` updated with actual commands and outcomes.

## Test pyramid

1. **Domain unit:** URL parse, evidence, verdict, state transition, redaction, fixture consistency.
2. **UI/unit:** Compose/SwiftUI view state, controls, a11y labels, loading/failure copy.
3. **Integration:** device picker/camera/OCR, encrypted history (if selected), API failure mapping.
4. **Backend:** rate limit, malicious request, provider timeout/429/5xx, cache and logs.
5. **E2E:** cross-platform source → consent → check → evidence → safer step, with simulated provider.
6. **Manual:** screenshots on representative devices and both themes, large type, screen reader, offline.

## Release blockers

- Missing legally compatible provider, inadequate privacy disclosure, hidden data upload, untested verdict semantics, insecure server-side URL handling, leaked credentials, inaccessible critical warning or any flow that automatically opens an untrusted URL.

## CI plan

After local baseline, add GitHub Actions separately:
- Android: Linux + installed Android SDK/Gradle, lint and tests.
- iOS: macOS runner with selected Xcode version, build/test destination.
- Shared: secret scanning, dependency review, schema and fixture checks.

Do not invent CI credentials or signing identities.
