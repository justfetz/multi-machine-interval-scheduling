# Multi-Machine Interval Scheduling

Java, Python, and OR-Tools implementations of a fixed-window multi-machine job scheduling problem.

This repository is the polished successor to the older school project repo. The original coursework version still belongs in the learning bucket, while this version is meant to be the public technical showcase.

## Problem

We are given:

- `N` machines
- `M` jobs
- a fixed start and end time for each job

Each job must be assigned to at most one machine, and overlapping jobs cannot be assigned to the same machine.

## Why this repo exists

This project is meant to show the same operations research problem through multiple implementation styles:

- Java baseline solver
- Python baseline solver
- OR-Tools optimizer-backed solver

The goal is not only to solve the problem, but to package the solution in a way that other people can download, run, inspect, and compare.

## Goal

The first public version will support a simple objective:

- maximize the number of scheduled jobs

Later versions can add:

- weighted jobs
- machine capabilities
- setup costs
- tardiness penalties
- load balancing

## Repository layout

```text
multi-machine-interval-scheduling/
  input/
  output/
  docs/
  java/
  python/
  ortools-python/
  ortools-java/
```

Core Java source lives in:

- [`java/src/mmis/Main.java`](./java/src/mmis/Main.java)
- [`java/src/mmis/GreedyScheduler.java`](./java/src/mmis/GreedyScheduler.java)
- [`java/src/mmis/Job.java`](./java/src/mmis/Job.java)
- [`java/src/mmis/MachineSchedule.java`](./java/src/mmis/MachineSchedule.java)
- [`java/src/mmis/CsvJobReader.java`](./java/src/mmis/CsvJobReader.java)
- [`java/src/mmis/CsvScheduleWriter.java`](./java/src/mmis/CsvScheduleWriter.java)

If you are viewing the repo on GitHub, open the [`java/src/mmis/`](./java/src/mmis/) folder directly.

## Reproducible commands

Java baseline:

```powershell
cd java
.\run-java.ps1
```

Java tests:

```powershell
cd java
.\run-java-tests.ps1
```

Python greedy baseline:

```powershell
cd python
.\run-python.ps1
```

Python OR-Tools baseline:

```powershell
cd ortools-python
.\run-ortools-python.ps1
```

Java OR-Tools baseline:

```powershell
cd ortools-java
.\run-ortools-java.ps1
.\run-ortools-java-tests.ps1
```

Run every track:

```powershell
.\run-all.ps1
```

## Sample input

See [`input/sample_jobs.csv`](./input/sample_jobs.csv).

## Verified sample outputs

Greedy baseline reference:

- [`output/sample_schedule_greedy_java.csv`](./output/sample_schedule_greedy_java.csv)
- [`output/sample_unassigned_jobs_java.csv`](./output/sample_unassigned_jobs_java.csv)

Python greedy mirror:

- [`output/sample_schedule_greedy_python.csv`](./output/sample_schedule_greedy_python.csv)
- [`output/sample_unassigned_jobs_python.csv`](./output/sample_unassigned_jobs_python.csv)

OR-Tools CP-SAT result:

- [`output/sample_schedule_ortools_python.csv`](./output/sample_schedule_ortools_python.csv)
- [`output/sample_unassigned_jobs_ortools_python.csv`](./output/sample_unassigned_jobs_ortools_python.csv)

## Java implementation

The Java baseline is a simple, readable greedy solver built around explicit domain classes instead of the original school-project array logic.

Key classes:

- `Main.java`: CLI entry point
- `CsvJobReader.java`: reads jobs from CSV
- `GreedyScheduler.java`: earliest-finish greedy assignment
- `MachineSchedule.java`: per-machine schedule state
- `CsvScheduleWriter.java`: writes assigned and unassigned CSV outputs
- `SolverResult.java`: final result payload

The code is intentionally plain Java so someone can clone the repo and follow the solver without needing a framework.

## Planned implementations

### Java

- clean greedy baseline
- CSV input/output
- verified sample run

### Python

- matching greedy baseline
- same input/output contract
- sample test added

### OR-Tools Python

- CP-SAT formulation
- maximize assigned jobs
- sample test added

### OR-Tools Java

- CP-SAT parity implementation
- same CSV input/output contract
- Maven-backed Java build

## Current status

Completed:

1. Refactored the original school concept into explicit domain objects.
2. Replaced `input.txt` with CSV input.
3. Wrote schedule and unassigned outputs as CSV.
4. Added Python and OR-Tools sample tests.
5. Added a lightweight Java regression harness with no external test framework.
6. Verified Java, Python, and OR-Tools runs on the same sample instance.

## Next steps

1. Compare greedy vs CP-SAT behavior on richer benchmark inputs.
2. Add fixed machine capabilities or weighted job variants.
3. Repeat this same structure across additional scheduling families.
