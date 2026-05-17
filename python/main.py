import sys
from pathlib import Path

from solver.greedy_solver import solve_greedy
from solver.io_utils import read_jobs, write_schedule, write_unassigned


def main() -> int:
    if len(sys.argv) < 5:
        print("Usage: python main.py <input.csv> <machine-count> <schedule-output.csv> <unassigned-output.csv>")
        return 1

    input_path = Path(sys.argv[1])
    machine_count = int(sys.argv[2])
    schedule_output_path = Path(sys.argv[3])
    unassigned_output_path = Path(sys.argv[4])

    jobs = read_jobs(input_path)
    scheduled, unassigned = solve_greedy(jobs, machine_count)

    write_schedule(schedule_output_path, scheduled)
    write_unassigned(unassigned_output_path, unassigned)

    print(f"Scheduled {len(scheduled)} jobs and left {len(unassigned)} unassigned.")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
