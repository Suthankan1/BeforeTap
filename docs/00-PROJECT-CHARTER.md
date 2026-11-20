# Product charter

## Problem

Phishing links, impersonation messages and malicious QR codes can look legitimate. People need an understandable pause before opening a link, not a misleading green checkmark.

## Product promise

BeforeTap helps a user inspect the actual destination, locally identify warning signals, optionally request reputational evidence, and understand uncertainty. It teaches independent verification using the official website or trusted contact channel.

**Audience:** mobile-first general consumers, particularly people encountering suspicious SMS, chat, email, social posts or QR codes.

**North-star behavior:** more users choose an independently verified route instead of immediately opening an untrusted link.

## MVP in scope

1. Paste a URL or message.
2. Select a screenshot and extract text on device.
3. Scan QR and preview the decoded destination without opening it.
4. Show four accurate verdict states, signals, explanation and safer next steps.
5. Optional consent-gated reputation lookup once a real backend exists.
6. Local history with clear/delete controls after privacy requirements are accepted.
7. Accessible native interaction on both platforms.

## Explicitly out of scope for first release

- Browser engine, automatic crawling, sandbox detonation, malware scanning of binaries.
- Bank credential retrieval, password entry, payment processing or browser auto-login.
- Claiming 100% accuracy or providing a "guaranteed safe" verdict.
- Cloud OCR, account creation, cross-device sync and behavioral ad tracking.
- A single cross-platform UI framework.

## Success measures (hypotheses; not measured)

- Users comprehend "no known threats" vs "safe" in usability studies.
- Users can complete an inspection without unintentionally opening a URL.
- Screen reader and large-text flows remain usable.
- Failed/partial provider checks produce UNABLE_TO_VERIFY, never clean.
- Privacy and retention controls are discoverable.

## Required research before launch

Threat-provider commercial license, jurisdiction-specific privacy obligations, App Store/Play policies, incident reporting links and support channels. Do not invent legal compliance.
