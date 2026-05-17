# Implementation Comparison

This repository is meant to show the same scheduling problem across multiple implementation styles.

## Tracks

- Java greedy baseline
- Python greedy baseline
- Python OR-Tools CP-SAT

## What the comparison shows

### Java greedy baseline

- strong fit for demonstrating the original school project evolution
- explicit object model
- straightforward CLI packaging

### Python greedy baseline

- easier to read and extend
- good for quick experimentation
- useful bridge between textbook algorithm and optimizer model

### Python OR-Tools CP-SAT

- solver-backed assignment model
- clean way to add richer constraints later
- strongest signal for operations research and practical optimization

## Current sample result

All three implementations currently schedule 8 jobs and leave 3 unassigned on the sample instance.

The greedy and OR-Tools variants do not necessarily choose the same exact assignment pattern, which is useful in itself:

- the greedy baseline gives a deterministic heuristic schedule
- the OR-Tools model gives an optimizer-backed schedule under the stated objective
