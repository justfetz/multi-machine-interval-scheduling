#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
mkdir -p out
javac -d out src/mmis/*.java tests/TestGreedyScheduler.java
java -cp out mmis.tests.TestGreedyScheduler
