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
- Native app builds, Android unit tests and Android lint are still pending.

## Bootstrap checkpoint

- Starting revision `f84715e`, branch `main`, initially clean and tracking `origin/main` at the requested GitHub repository.
- `python3 scripts/validate_repository.py` passed for 9 required files and 4 fictional verdicts.
- `swiftc -frontend -parse apps/ios/BeforeTap/BeforeTapApp.swift apps/ios/BeforeTap/Domain/*.swift apps/ios/BeforeTap/Features/*.swift` passed. Swift 6.4.2 also type-checked the Foundation-only domain model with a writable temporary module cache.
- `xcodebuild -version` failed because only Command Line Tools were selected. `xcrun --sdk iphonesimulator --show-sdk-path` failed to find the simulator SDK. XcodeGen was absent.
- Cached Gradle 9.7.1 on OpenJDK 25.0.4.1 attempted `:app:assembleDebug :app:testDebugUnitTest :app:lintDebug` in offline mode. Configuration failed at `com.android.application:9.5.1` resolution. The configured Android SDK directory was absent and the project's Gradle 9.6.0 wrapper distribution was incomplete in the cache.
- Figma frame metadata was accessible for both prototypes. Welcome, privacy, check home and choose source are iOS `4:194`, `4:227`, `4:258`, `4:320` and Android `2:16`, `2:49`, `2:80`, `2:142`. Capture the matching frame context and screenshots before implementing each screen.

## Next exact slice

Finish **M0.1** with full Xcode, XcodeGen, and an installed iOS simulator runtime. Resolve the pinned Android plugins online, install the configured SDK, generate the wrapper and run the three Android checks above. See [local setup](docs/09-SETUP.md). Continue with M1.1 fixture decoding on both platforms. Keep all results labeled as demos.

## Session log template

- Date / tool / prompt:
- Scope and files changed:
- Commands/tests and results:
- Screenshot/accessibility review:
- Security/privacy implications:
- Decision needed:
- Next slice:
