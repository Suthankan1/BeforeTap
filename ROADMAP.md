# Milestone roadmap (prompt one slice at a time)

Do not implement everything in one prompt. The checkboxes track *verified* work, not agent-generated code.

## M0 — Bootstrap

- [x] M0.1 Build both demo shells. Android debug APK and iOS simulator builds pass.
- [ ] M0.2 Document build/test commands, versions, signing and emulator/device setup.
- [ ] M0.3 Establish formatting, test target and smoke test. Host verdict checks pass on both languages. Native test and lint verification is pending.

## M1 — Pure domain

- [x] M1.1 Verdict/evidence models and fixture decoding with host tests on both platforms.
- [ ] M1.2 URL input parser and normalization with edge-case tests. Both platforms pass the shared single-link ASCII cases. IDN handling and message extraction are pending.
- [ ] M1.3 Explainable local indicators with limits (not a threat reputation service).
- [ ] M1.4 State machine: idle, input, local check, optional remote, result, errors.

## M2 — Native UI against Figma

- [ ] M2.1 iOS onboarding, privacy and check home (I01-I04). I01 and I02 compile. Simulator verification and I03–I04 remain.
- [ ] M2.2 Android onboarding, privacy and check home (A01-A04). A01 and A02 verified on the Android emulator. A03 and A04 remain.
- [ ] M2.3 Paste/review/result states, all four verdicts (I05/I09-I15 and Android equivalents).
- [ ] M2.4 Evidence details, history, settings, help, offline (I14-I20 and equivalents).
- [ ] M2.5 Dark mode, Dynamic Type/font scaling, screen readers, reduced motion, touch targets.

## M3 — Device input

- [ ] M3.1 iOS Vision OCR and Photos picker with no background image upload.
- [ ] M3.2 Android ML Kit/on-device OCR with Android photo picker.
- [ ] M3.3 Camera-based QR scanning with explicit permission and preview-before-open.
- [ ] M3.4 Shared input tests: malformed, mixed-script, encoded and ambiguous URLs.

## M4 — Real verification

- [ ] M4.1 Resolve vendor licensing, pricing, consent, retention, region and legal review.
- [ ] M4.2 Implement server-side gateway behind contract with strict SSRF defenses and no active URL fetch.
- [ ] M4.3 Integrate lookup provider with timeout, rate limits, cache TTL and attribution.
- [ ] M4.4 End-to-end tests: positive threat, unknown, provider down, stale response, partial check.
- [ ] M4.5 Security review and external assessment before production claims.

## M5 — Release

- [ ] M5.1 Data deletion, retention, privacy disclosure and support/reporting.
- [ ] M5.2 Telemetry redaction, opt-in analytics decision, auditability without raw messages.
- [ ] M5.3 CI signing strategy, device matrix, accessibility/regression and release checks.
- [ ] M5.4 Store policy, app permissions, security copy and beta feedback.
