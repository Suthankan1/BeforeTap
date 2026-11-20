# Figma screen inventory and behavior

Sources:
- [iOS Figma](https://www.figma.com/design/W7mn2g5KWER37FEFTeCal9)
- [Android Figma](https://www.figma.com/design/TeWROoR3LlljunpY0j6Vvc)

Both files contain the same logical journey, with native styling rather than identical UI trees.

| ID | Screen | Primary contract |
|---|---|---|
| 01 | Welcome | Product trust promise, start |
| 02 | Privacy promise | Explain local processing and optional lookup |
| 03 | Check home | Paste, screenshot, QR; history/settings tabs |
| 04 | Choose source | Source selection without URL opening |
| 05 | Paste text or URL | Input, validation, editing |
| 06 | Screenshot import | System picker, no default upload |
| 07 | Review extracted text | User verifies OCR output |
| 08 | QR scanner | Camera, manual fallback, decoded preview |
| 09 | Checking | Which checks run locally/online |
| 10 | Known malicious | Confirmed reputation evidence, stop/safer action |
| 11 | Suspicious | Local risk indicators, independent verification |
| 12 | No known threats | "No match" caveat, uncertainty |
| 13 | Unable to verify | Timeout/offline/provider fail, retry |
| 14 | Evidence detail | Source, explanation, freshness and limitations |
| 15 | Safe next steps | Independent official-channel verification |
| 16 | History | Local checks, deletion and retention |
| 17 | Saved result | Historical timestamp/status, not re-evaluated now |
| 18 | Settings | Permission/remote lookup/retention controls |
| 19 | Help and reporting | Post-click advice and reporting links |
| 20 | Offline | Local checks only, incomplete lookup warning |

## Interaction rules

- Primary Check routes to source entry. Evidence controls open detailed reasoning.
- Tap a domain only after an explicit confirmation; default result screen never autoloads an untrusted URL.
- Back/cancel preserves edits safely and does not silently upload.
- A history item displays the **original check time**, not a current clean bill of health.
- The mock "checking" result picker is for design review only; never ship it as real detection.

## Content rules

Use fictional `.example` domains in screenshots and tests. Avoid real phishing URLs or accounts. Use a short, plain-language explanation for every technical indicator.
