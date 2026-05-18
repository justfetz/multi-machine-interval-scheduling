# Java Track

This folder now contains the first cleaned Java baseline implementation.

## Current behavior

- reads jobs from a CSV file
- sorts by earliest end time
- assigns each job to the first machine that can take it without overlap
- writes scheduled and unassigned outputs as CSV

## Compile

```bash
javac -d out src/mmis/*.java
```

## Run

```bash
java -cp out mmis.Main ../input/sample_jobs.csv 3 ../output/sample_schedule_greedy_java.csv ../output/sample_unassigned_jobs_java.csv
```

## Test

```bash
powershell -ExecutionPolicy Bypass -File .\run-java-tests.ps1
```

The test harness is dependency-free and currently checks:

- sample instance scheduled vs unassigned counts
- no overlap on any machine in the medium sample
- invalid jobs are rejected by the domain model

## Current files

- `src/mmis/`: solver, models, CSV I/O, and CLI entry point
- `tests/TestGreedyScheduler.java`: lightweight regression harness
- `run-java.ps1`: compile and run the sample instance
- `run-java-tests.ps1`: compile and run the Java tests

## Planned next improvements

- richer solver summaries
- machine capability constraints
- weighted-job variants
