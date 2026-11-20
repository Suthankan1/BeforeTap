# Security review prompt

```text
Review this bounded BeforeTap change as a skeptical mobile/application security engineer.
Read docs/04-VERIFICATION-ENGINE.md, docs/05-THREAT-MODEL.md, docs/06-PRIVACY.md
and contracts/verification.openapi.yaml.

Find concrete exploit paths, false-safe results, hidden URL fetches/redirects,
SSRF, provider key exposure, retention/logging leaks, bad normalization,
permission creep and misleading source attribution.
Separate proven issues from hypotheses. Give file/line references, severity,
minimal reproduction and a small fix/test proposal. Do not modify files.
```
