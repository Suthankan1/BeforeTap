# Local setup and bootstrap

This package includes **native source shells and configuration**, but not generated platform output or signed binaries. Validate the toolchain before feature work.

## iOS (macOS required)

1. Install an Xcode version supporting the selected iOS SDK and SwiftUI APIs.
2. Install **XcodeGen** (https://github.com/yonaskolb/XcodeGen) or create a standard iOS App project manually.
3. In `apps/ios`, run `xcodegen generate` to create the Xcode project from `project.yml`.
4. Open the generated `BeforeTap.xcodeproj` in Xcode. Select a personal development team and simulator.
5. Build/run the `BeforeTap` target. Observe that the demo result is clearly labeled.
6. If XcodeGen config/SDK changes are required, record actual versions and compile logs in `STATUS.md`.

The generated `.xcodeproj` is ignored because the YAML source is authoritative. If the team prefers a checked-in Xcode project, decide and document the change.

## Android

1. Install current Android Studio, JDK 17+, SDK Platform API 37 and compatible build tools.
2. Open `apps/android` and let Android Studio import the Gradle project.
3. This archive omits `gradle-wrapper.jar` (binary); generate a standard trusted wrapper via installed Gradle `gradle wrapper --gradle-version 9.6.0`, or use Android Studio's wrapper workflow.
4. Run `./gradlew :app:assembleDebug :app:testDebugUnitTest` once a wrapper is present.
5. Use an emulator/device to check the demonstration UI and system insets. Never represent the demo result as a network scan.

The Android version baseline is a proposed November 2026 selection (AGP 9.5.1, Compose BOM 2026.09.00, Kotlin Compose plugin 2.4.21). Verify compatibility in Android Studio and document any deliberate upgrades rather than applying them blindly.

## What has actually been verified

The ZIP contents, structure and JSON/YAML syntax are checked during packaging. **No iOS compilation or Android Gradle build was possible in the Linux packaging environment**. The first prompt should establish these baselines.

## Official docs

- https://developer.apple.com/documentation/swiftui/
- https://developer.android.com/develop/ui/compose/setup
- https://developer.android.com/build/releases/about-agp
- https://developer.android.com/develop/ui/compose/bom
