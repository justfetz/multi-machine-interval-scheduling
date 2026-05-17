# OR-Tools Model Notes

## Decision variables

For each job `j` and machine `m`, define:

- `x[j, m] in {0, 1}`

Where:

- `1` means job `j` is assigned to machine `m`
- `0` means it is not

## Constraints

### At most one machine per job

For each job:

- `sum_m x[j, m] <= 1`

### No overlapping jobs on the same machine

For each machine and for each overlapping job pair:

- `x[j1, m] + x[j2, m] <= 1`

## Objective

Maximize:

- total assigned jobs

## Future extensions

- weighted objective by job value
- machine eligibility matrix
- idle-time penalties
- machine balancing objective
- precedence relationships
