# Native UI/UX implementation contract

## iOS — SwiftUI / Liquid Glass

- Follow the iOS Figma flow and system navigation conventions. Use `TabView`, native sheets, system pickers, Dynamic Type, VoiceOver and large hit targets.
- Liquid Glass is a **functional layer for navigation and controls**, not a filter applied to every content card. Prefer standard SwiftUI components and their adaptive system appearance. Use custom `glassEffect` only where justified.
- Respect safe areas, scroll-edge behavior, reduced transparency and reduced motion.
- Use SF system typography in the real app. Figma may show a rendering fallback.
- Let Apple generate status bar and Dynamic Island system chrome at runtime; Figma hardware silhouettes are only visual mockups.
- Do not hardcode a simulated status bar, notch or battery overlay in the shipped app.

## Android — Jetpack Compose / Material 3 Expressive

- Use `MaterialTheme`, Material 3 `Scaffold`, `NavigationBar`, cards, tonal surfaces, large touch targets and OS-managed status/navigation bars.
- Material Expressive may use emphasized type, stronger shape variety, active nav indicator pills and motion physics. Avoid copying iOS glass onto Android.
- Allow dynamic color where supported and provide a coherent product fallback palette.
- Support TalkBack, font scaling, edge-to-edge, insets and system back navigation.
- Preview components at compact phone, large phone and accessible text scales.

## Shared semantic colors (not identical OS paint values)

| Token | Meaning |
|---|---|
| brand | Trusted action emphasis |
| text-primary | High-contrast reading |
| text-secondary | Supportive text with contrast |
| surface | Content cards |
| critical | Confirmed known malicious |
| warning | Suspicious indicators |
| positive | No known threats *with caveat* |
| unknown | Missing/failed evidence |

Never use color alone for status: include icon, text, evidence and accessibility label. Do not use a celebratory green shield suggesting guaranteed safety.

## Accessibility acceptance

- Screen reader announces verdict and what was/was not checked.
- Dynamic Type / 200% font scale does not clip controls.
- Tap targets at least platform minimum; keyboard/switch navigation works.
- Color contrast meets relevant WCAG success criteria; semantic labels not "red" or "green" only.
- Reduce motion and reduced transparency do not remove meaning.
- Risk result has non-color explanation, timestamp and safe next action.

## Motion

- iOS: system transitions and navigation material, minimal custom glass animations.
- Android: Material motion principles/springs where supported, no delayed critical warning.
- No animation that makes an unverified URL look "approved".

## External references

- Apple materials: https://developer.apple.com/design/human-interface-guidelines/materials
- Apple SwiftUI glass: https://developer.apple.com/documentation/swiftui/view/glasseffect(_:in:)
- Material 3: https://m3.material.io/
- Compose: https://developer.android.com/develop/ui/compose
