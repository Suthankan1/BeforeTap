# Working across Antigravity, Codex, Claude Code and Cursor

## One shared truth

`AGENTS.md` is canonical, `CLAUDE.md`, `.cursor/rules` and `.agent/rules` are pointers. `STATUS.md` records verified results; `DECISIONS.md` records accepted decisions. Keep the rules short so tools do not start with conflicting walls of context.

## Session recipe

1. Select a single milestone in `ROADMAP.md`.
2. Ask the agent to **read and plan only**, with explicit acceptance criteria.
3. Approve the smallest edit, not an entire app.
4. Run local build/tests; ask another agent for a read-only review using `prompts/04-HANDOFF-AND-REVIEW.md`.
5. Fix actual issues, compare a device screenshot with Figma, update `STATUS.md`.
6. Commit only after passing evidence. Start a new slice.

## Example handoff

"Continue M1.2 URL parser only. Read AGENTS.md, STATUS.md, docs/04-VERIFICATION-ENGINE.md. First inspect the existing tests; do not edit UI or backend. Produce test cases for user-info, punycode, percent encoding and unsupported schemes. Then implement the smallest parser and run focused tests."

## Avoid

- Sending a giant "build everything" prompt and accepting unreviewed output.
- Giving credentials to the model as prompt text.
- Letting one tool overwrite uncommitted edits from another.
- Measuring progress by generated files instead of verified behavior.
- Accepting visual-only prototype states as security implementation.

## Recommended commits

`chore: bootstrap ios project`, `chore: bootstrap android project`, `test: define verdict states`, `feat: parse web link inputs locally`. One logical change per commit.
