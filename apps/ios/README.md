# iOS source shell

SwiftUI target configured via `project.yml` for XcodeGen. Generate the Xcode project on macOS. The UI intentionally uses native `TabView` and a system button style so iOS owns the adaptive navigation material; do **not** render the Figma Dynamic Island/status icons in app content.

Files:
- `BeforeTapApp.swift`: app entry
- `Domain/VerificationModels.swift`: pure model and demo fixture
- `Features/CheckHomeView.swift`: native check home and disclaimer
- `Features/DemoResultView.swift`: explicitly non-live sample
- `Features/SupportingViews.swift`: empty history and privacy settings

Next: native bootstrap compilation, domain unit tests, and screen-by-screen Figma matching. No real check API or scanner exists.
