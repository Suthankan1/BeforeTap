# Verification API contract

The OpenAPI document is **proposed**, not deployed. It excludes an endpoint that downloads remote pages. The URL field is for licensing-compliant *reputation lookup*, not navigation or SSRF-prone fetching.

Before implementation, settle auth, provider/legal terms, consent, TTL, redaction, error taxonomy and response provenance. Introduce contract tests on both native clients and backend. Do not interpret 200 with empty evidence as safe unless the required lookup truly completed.
