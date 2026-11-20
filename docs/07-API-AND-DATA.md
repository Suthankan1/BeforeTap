# API and data contract

The `contracts/verification.openapi.yaml` document is a **proposed v1 contract**, not a deployed endpoint.

## Client responsibilities

- Validate input before sending.
- Use explicit consent and the minimum data required.
- Configure strict request timeout and cancellation.
- Do not send a raw screenshot.
- Render provider failures as incomplete.
- Never embed provider API keys.

## Server responsibilities (future)

- Authenticate/rate-limit requests, protect vendor credentials and constrain payload sizes.
- Perform *reputation lookup only*; no untrusted URL fetch.
- Attribute each signal and set checked/observed timestamps.
- Return structured results and stable error categories.
- Apply cache freshness, redaction, regional processing and retention policy.

## Local persistence (future)

History record: generated ID, timestamp, input type, safe display host, verdict, evidence summary, source metadata and limitations. Raw message bodies/query secrets should not be persisted by default.

## Contract evolution

Use explicit versions and additive fields. Unknown future status values should map to `UNABLE_TO_VERIFY` in older clients, not "no threats". Define migrations before persistence ships.
