# Privacy and data handling

## Defaults to implement

- Process pasted messages, screenshots and QR locally where technically feasible.
- Ask for camera/photo permission only when the action requires it.
- Remote reputation lookup must show exactly what leaves the device and request consent. Prefer minimum URL data compatible with provider.
- Store no history until owner decides a retention default. When added, local deletion must be clear and testable.
- No raw message/screenshot or full URL with tokens in analytics/crash logs.
- No ad identifiers, contact scraping, accessibility-service surveillance or automatic clipboard reading.

## Product copy examples

- "We analyze text on your device. Online reputation checks are optional and may send the link to our verification provider."
- "No known threats found does not guarantee this website is safe."
- "Couldn't complete the online check. Please verify through the organization's official app or website."

## Decisions needed before launch

| Decision | Owner choice |
|---|---|
| Default history retention | Pending |
| Online lookup opt-in vs one-time consent | Pending |
| Data processing region | Pending |
| Vendor and contract | Pending |
| Server log TTL and redaction | Pending |
| User deletion and support request | Pending |
| Child safety policy, legal notice | Pending |

Do not write a privacy policy claiming completed controls that do not exist in code. Seek jurisdiction-specific legal review.
