from pathlib import Path
import sys

sys.path.append(str(Path(__file__).resolve().parents[1]))

from solver.cp_sat_solver import solve_cp_sat
from solver.io_utils import read_jobs


def test_cp_sat_sample_instance():
    jobs = read_jobs(Path(__file__).resolve().parents[2] / "input" / "sample_jobs.csv")
    scheduled, unassigned = solve_cp_sat(jobs, 3)

    scheduled_rows = [(item.machine_id, item.job.job_id) for item in scheduled]
    unassigned_ids = [job.job_id for job in unassigned]

    assert len(scheduled) == 8
    assert len(unassigned) == 3
    assert scheduled_rows == [
        ("M1", "J1"),
        ("M1", "J5"),
        ("M2", "J9"),
        ("M3", "J8"),
        ("M3", "J10"),
        ("M3", "J7"),
        ("M3", "J4"),
        ("M3", "J11"),
    ]
    assert unassigned_ids == ["J2", "J3", "J6"]
