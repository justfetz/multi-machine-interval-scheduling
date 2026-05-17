# Complexity Notes

## Greedy baseline

Let:

- `M` be the number of jobs
- `N` be the number of machines

The current greedy baseline:

1. sorts jobs in `O(M log M)`
2. scans up to `N` machines per job

Overall:

- `O(M log M + M * N)`

This is a reasonable public baseline because it is:

- simple
- deterministic
- performant enough for small to medium teaching examples

## OR-Tools baseline

The CP-SAT model is not best described by a single asymptotic runtime, because actual solve time depends on:

- instance structure
- number of overlapping jobs
- machine count
- solver search behavior

For this reason, the public repo should present the OR-Tools version as:

- an optimization-backed reference implementation
- not necessarily the fastest approach for every small instance
