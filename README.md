# BeforeTap — native iOS + Android

**A privacy-first link and message verification assistant.** BeforeTap helps people pause, inspect a URL or message, understand evidence, and choose safer next steps. It never promises that a link is safe.

This repository is a **prompt-first, human-reviewed starter** for two separate native applications:

- **iOS:** Swift 6 / SwiftUI, Apple-native navigation and iOS 26 Liquid Glass where appropriate.
- **Android:** Kotlin / Jetpack Compose / Material 3 Expressive.
- **Verification backend:** contract and design only; no live service is included yet.

The starting apps are educational **UI shells with explicitly labeled demo results**, not a threat-scanning product. No vendor API credentials, live threat intelligence, screenshot OCR or QR implementation is bundled.

## Start here

1. Read [AGENTS.md](AGENTS.md), [docs/00-PROJECT-CHARTER.md](docs/00-PROJECT-CHARTER.md) and [docs/04-VERIFICATION-ENGINE.md](docs/04-VERIFICATION-ENGINE.md).
2. Open both Figma files under the same design team:
   - [iOS native prototype](https://www.figma.com/design/W7mn2g5KWER37FEFTeCal9)
   - [Android native prototype](https://www.figma.com/design/TeWROoR3LlljunpY0j6Vvc)
3. Read [docs/09-SETUP.md](docs/09-SETUP.md) for Xcode/Android Studio setup and validation limits.
4. Start at [prompts/00-START-HERE.md](prompts/00-START-HERE.md), then implement one milestone from [ROADMAP.md](ROADMAP.md).
5. Keep [STATUS.md](STATUS.md) and [DECISIONS.md](DECISIONS.md) current after each verified slice.

## Repo map

| Directory | Purpose |
|---|---|
| `apps/ios` | XcodeGen definition and SwiftUI source starter |
| `apps/android` | Android/Compose Gradle source starter |
| `contracts` | Proposed versioned verification API, not yet implemented |
| `fixtures` | Safe fictional sample inputs and results |
| `docs` | Requirements, architecture, security, UI and QA specifications |
| `prompts` | Copy-ready task prompts and review prompts |
| `.agent` | Antigravity rules and repeatable workflow |
| `.cursor` | Cursor project rule |
| `reference` | Earlier product design PDF + LaTeX source |

## Golden rules

- **Native means native:** no Flutter, React Native, Ionic, webview UI or forced shared UI framework.
- **No invented security assurance:** "No known threats" does not mean safe.
- **No untrusted network fetch** in mobile clients or a future backend until SSRF-safe design is reviewed.
- **No secrets in clients or prompts**; obtain user consent before sending a URL or message to a third-party provider.
- **No silent production claims:** mark prototype, stub, mock and unverified behavior accurately.
- **No giant agent-built feature dumps:** request one bounded change, inspect diffs, run tests, review UX, then commit.

## Current state

The Figma flows each have 20 screens and native styling. The source code here only starts the home/demo journey. The accompanying plan maps all remaining screens and states. The Gradle and Xcode projects **have not been compiled in this Linux environment** (no Android SDK/Gradle or Xcode installed); establish a clean baseline on your Mac/Android Studio before feature work.

Copyright © 2026 Suthankan Balenthiran. Licensing decision pending; do not assume open-source permission.
