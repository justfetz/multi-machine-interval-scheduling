# Algorithm Notes

## Problem framing

This project models a fixed-window scheduling problem:

- each job has a known start and end time
- each machine can process at most one overlapping job at a time
- the current objective is to maximize the number of assigned jobs

## Greedy baseline

The greedy baseline sorts by:

1. earliest end time
2. earliest start time
3. stable job identifier tie-break

Then it assigns each job to the first machine that can accept it without overlap.

### Why this is useful

- easy to explain
- easy to run locally
- deterministic
- good baseline against optimizer-backed results

## OR-Tools CP-SAT model

The OR-Tools track creates:

- a binary assignment variable for each job-machine pair
- at-most-one-machine constraints for every job
- pairwise no-overlap constraints per machine

### Current objective

- maximize the number of assigned jobs

### Why this matters

It gives a cleaner path toward richer future constraints such as:

- machine eligibility
- setup penalties
- weighted jobs
- balancing workloads across machines
