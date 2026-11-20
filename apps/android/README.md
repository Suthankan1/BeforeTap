# Android source shell

Android Studio project, proposed AGP 9.5.1 / Kotlin Compose plugin 2.4.21 / compileSdk 37 / Material 3 Compose BOM 2026.09.00. Uses AGP 9 built-in Kotlin support; do not apply the old `kotlin-android` plugin without understanding the new DSL.

Install the Android SDK packages in [local setup](../../docs/09-SETUP.md), then run `./gradlew :app:assembleDebug :app:testDebugUnitTest :app:lintDebug`.

The app is a small demo UI and does not inspect pasted input or contact a reputation provider. Its demo status is always visible.
