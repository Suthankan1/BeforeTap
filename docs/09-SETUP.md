# Local setup and bootstrap

This package includes **native source shells and configuration**, but not generated platform output or signed binaries. Validate the toolchain before feature work.

## iOS (macOS required)

1. Install an Xcode version supporting the selected iOS SDK and SwiftUI APIs.
2. Install **XcodeGen** (https://github.com/yonaskolb/XcodeGen) or create a standard iOS App project manually.
3. In `apps/ios`, run `xcodegen generate` to create the Xcode project from `project.yml`.
4. Open the generated `BeforeTap.xcodeproj` in Xcode. Select a personal development team and simulator.
5. Build the simulator target with `xcodebuild -project BeforeTap.xcodeproj -scheme BeforeTap -destination "generic/platform=iOS Simulator" build`. Run on an installed simulator to check that the demo result is clearly labeled.
6. If XcodeGen config/SDK changes are required, record actual versions and compile logs in `STATUS.md`.

The generated `.xcodeproj` is ignored because the YAML source is authoritative. If the team prefers a checked-in Xcode project, decide and document the change.

## Android

1. Install a compatible JDK and Android SDK. The command-line package IDs for this project are `platforms;android-37.0` and `build-tools;36.0.0`.
2. Open `apps/android` and let Android Studio import the Gradle project.
3. Use the checked-in wrapper. It downloads Gradle 9.6.0 and checks the distribution SHA-256.
4. Run `./gradlew :app:assembleDebug :app:testDebugUnitTest :app:lintDebug` once a wrapper is present.
5. Use an emulator/device to check the demonstration UI and system insets. Never represent the demo result as a network scan.

The Android version baseline is a proposed November 2026 selection (AGP 9.5.1, Compose BOM 2026.09.00, Kotlin Compose plugin 2.4.21). Verify compatibility in Android Studio and document any deliberate upgrades rather than applying them blindly.

## Domain check with macOS Command Line Tools

From the repository root:

```sh
beforetap_test_dir="$(mktemp -d)"
swiftc -module-cache-path "$beforetap_test_dir/cache" -swift-version 6 \
  apps/ios/BeforeTap/Domain/*.swift \
  apps/ios/Tests/VerdictSmokeTests.swift -o "$beforetap_test_dir/verdict-tests" &&
  "$beforetap_test_dir/verdict-tests"
```

See [implementation status](../STATUS.md) for actual native build attempts and host test results.

## Official docs

- https://developer.apple.com/documentation/swiftui/
- https://developer.android.com/develop/ui/compose/setup
- https://developer.android.com/build/releases/about-agp
- https://developer.android.com/develop/ui/compose/bom
