# Reference links and version notes

These are the primary documentation sources consulted when assembling the November 2026 starter. They may change; revalidate while bootstrapping. Tool-specific instructions can also change between releases.

## Agent project instructions

- Codex AGENTS.md: https://developers.openai.com/cookbook/examples/codex/iterating-development-workflows-with-codex
- Claude Code memory/settings: https://code.claude.com/docs/en/settings
- Cursor project rules: https://docs.cursor.com/context/rules-for-ai
- Google Antigravity rules/workflows: https://codelabs.developers.google.com/getting-started-agy-ide

## Native development

- Apple materials: https://developer.apple.com/design/human-interface-guidelines/materials
- Apple Liquid Glass: https://developer.apple.com/documentation/technologyoverviews/liquid-glass
- SwiftUI: https://developer.apple.com/documentation/swiftui/
- Android Compose setup: https://developer.android.com/develop/ui/compose/setup
- Compose BOM: https://developer.android.com/develop/ui/compose/bom
- Android Gradle plugin compatibility: https://developer.android.com/build/releases/about-agp
- Kotlin releases: https://kotlinlang.org/docs/releases.html
- Material 3: https://m3.material.io/

## Security/provider

- Google Safe Browsing noncommercial restriction: https://developers.google.com/safe-browsing/reference
- Google Web Risk commercial option: https://docs.cloud.google.com/web-risk/docs/overview
- OWASP MASVS: https://mas.owasp.org/

## Version note

Android starter selects AGP 9.5.1, Gradle 9.6.0, Kotlin Compose plugin 2.4.21 and Compose BOM 2026.09.00 as a **proposed baseline**, not a locally build-verified combination. Verify in installed Android Studio. The project has no manually bundled Gradle wrapper executable/JAR.

iOS starter targets iOS 26 and requires macOS/Xcode, which were unavailable during packaging. A project's deployment target is a product choice, not a requirement to support older devices.
