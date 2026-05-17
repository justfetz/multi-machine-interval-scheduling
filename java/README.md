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

## Planned next improvements

- deterministic tests
- richer solver summaries
- machine capability constraints
- weighted-job variants
