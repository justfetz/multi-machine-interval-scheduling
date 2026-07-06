# AGENTS.md — rules for AI coding agents working in this repo

## What this repo is
A multi-track showcase of multi-machine interval scheduling (greedy + CP-SAT).
Tracks: `python/` (stdlib only), `java/` (JDK only), `ortools-python/`, `ortools-java/`.
Educational clarity beats cleverness.

## Invariants — do not break these
1. **Track parity.** Capabilities added to one baseline track (python/java) get
   mirrored in the other, or the gap is recorded in the root README and in
   `operations-research-algorithms/docs/implementation-matrix.md`.
2. **`python/` stays dependency-free** at runtime; pytest is the only dev dep.
   OR-Tools belongs only in the `ortools-*` tracks.
3. **CSV input/output contracts are frozen.** Check the sample files in
   `input/` and the writers before changing any column.
4. **User-facing failures must be actionable** (name the file/line/value);
   never let a bare KeyError/ValueError escape a main entry point.
5. **Ship both script flavors.** Any new `run-*.ps1` gets a `run-*.sh` twin and
   vice versa. Note: existing `.ps1` scripts hardcode a Windows JDK path —
   prefer `$env:JAVA_HOME` if you touch them.

## How to verify your work
- `cd python && ./run-tests.sh`
- `cd java && ./run-tests.sh`
- CI (`.github/workflows/ci.yml`) must pass; it runs every track.

## Out of scope for agents
- Do not edit generated files under `output/`.
- Do not add web frameworks, databases, or services.
- Do not rewrite history or force-push.
