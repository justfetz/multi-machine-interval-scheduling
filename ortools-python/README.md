# OR-Tools Python Track

This folder contains a CP-SAT model using Google OR-Tools.

Initial objective:

- maximize the number of assigned jobs

## Run

```bash
python main.py ../input/sample_jobs.csv 3 ../output/sample_schedule_ortools_python.csv ../output/sample_unassigned_jobs_ortools_python.csv
```

Later objectives can add:

- weighted jobs
- machine preference
- load balancing
