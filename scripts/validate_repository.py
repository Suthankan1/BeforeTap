#!/usr/bin/env python3
"""Offline structural and fixture checks; not a mobile build or security scanner."""
import json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
VERDICTS = {
    "KNOWN_MALICIOUS",
    "SUSPICIOUS",
    "NO_KNOWN_THREATS",
    "UNABLE_TO_VERIFY",
}


def main() -> None:
    required = [
        ROOT / "AGENTS.md",
        ROOT / "CLAUDE.md",
        ROOT / "ROADMAP.md",
        ROOT / "STATUS.md",
        ROOT / ".cursor/rules/beforetap.mdc",
        ROOT / ".agent/rules/beforetap.md",
        ROOT / "apps/ios/project.yml",
        ROOT / "apps/android/settings.gradle.kts",
        ROOT / "contracts/verification.openapi.yaml",
    ]
    missing = [str(p.relative_to(ROOT)) for p in required if not p.is_file()]
    assert not missing, f"Missing required files: {missing}"
    found = set()
    for path in sorted((ROOT / "fixtures/results").glob("*.demo.json")):
        payload = json.loads(path.read_text(encoding="utf-8"))
        assert payload["fixture"] is True, path
        assert payload["verdict"] in VERDICTS, path
        assert payload["displayHost"].endswith(".example"), path
        assert payload.get("limitations"), path
        assert isinstance(payload["checks"], list), path
        found.add(payload["verdict"])
    assert found == VERDICTS, f"Fixture verdict coverage mismatch: {found}"
    print(f"OK: {len(required)} required files and {len(found)} demo verdicts")
    print("NOTE: no Swift/Xcode, Android/Gradle or live verification tests were run.")


if __name__ == "__main__":
    main()
