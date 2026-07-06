#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
mkdir -p ../output
python3 main.py ../input/sample_jobs.csv 3 ../output/sample_schedule_greedy_python.csv ../output/sample_unassigned_jobs_python.csv
