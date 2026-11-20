# Prompt for screenshot OCR or QR input

```text
Implement only [iOS Vision screenshot OCR / Android on-device OCR /
iOS QR scan / Android QR scan].
Read AGENTS.md, docs/05-THREAT-MODEL.md, docs/06-PRIVACY.md,
docs/02-SCREEN-INVENTORY.md and the correct Figma screens.

Show permission purpose and cancellation states.
Keep image/text on-device; request no background upload or broad gallery permission.
Preview and let the user edit extracted content before verification.
Never open a decoded URL automatically or fetch remote preview content.
Handle multiple candidates, no candidate, invalid and encoded payloads.
Add tests or manual device checks and report the exact results.
Stop after this input slice, without adding reputation integration.
```
