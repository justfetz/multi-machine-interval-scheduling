# Problem Definition

This repository models a fixed-window job assignment problem.

Each job:

- has a known start time
- has a known end time
- may be assigned to at most one machine

Each machine:

- may process at most one job at a time

The baseline public solver uses a greedy heuristic. Later versions will use Google OR-Tools CP-SAT for a more formal optimization model.
