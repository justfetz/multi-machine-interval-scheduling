from pathlib import Path
import sys

sys.path.append(str(Path(__file__).resolve().parents[1]))

from solver.greedy_solver import solve_greedy
from solver.io_utils import read_jobs


def test_greedy_sample_instance():
    jobs = read_jobs(Path(__file__).resolve().parents[2] / "input" / "sample_jobs.csv")
    scheduled, unassigned = solve_greedy(jobs, 3)

    scheduled_rows = [(item.machine_id, item.job.job_id) for item in scheduled]
    unassigned_ids = [job.job_id for job in unassigned]

    assert len(scheduled) == 8
    assert len(unassigned) == 3
    assert scheduled_rows == [
        ("M1", "J1"),
        ("M1", "J10"),
        ("M1", "J7"),
        ("M1", "J4"),
        ("M1", "J11"),
        ("M2", "J8"),
        ("M2", "J2"),
        ("M3", "J9"),
    ]
    assert unassigned_ids == ["J3", "J6", "J5"]
