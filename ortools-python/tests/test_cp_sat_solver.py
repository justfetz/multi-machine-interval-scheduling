from pathlib import Path
import sys

sys.path.append(str(Path(__file__).resolve().parents[1]))

from solver.cp_sat_solver import solve_cp_sat
from solver.io_utils import read_jobs


def test_cp_sat_sample_instance():
    jobs = read_jobs(Path(__file__).resolve().parents[2] / "input" / "sample_jobs.csv")
    scheduled, unassigned = solve_cp_sat(jobs, 3)

    assert len(scheduled) == 8
    assert len(unassigned) == 3

    scheduled_ids = {item.job.job_id for item in scheduled}
    unassigned_ids = {job.job_id for job in unassigned}

    assert len(scheduled_ids) == 8
    assert len(unassigned_ids) == 3
    assert scheduled_ids.isdisjoint(unassigned_ids)
    assert scheduled_ids | unassigned_ids == {job.job_id for job in jobs}

    jobs_by_machine = {}
    for item in scheduled:
        jobs_by_machine.setdefault(item.machine_id, []).append(item.job)

    for machine_jobs in jobs_by_machine.values():
        machine_jobs.sort(key=lambda job: (job.start, job.end, job.job_id))
        for index in range(1, len(machine_jobs)):
            previous = machine_jobs[index - 1]
            current = machine_jobs[index]
            assert previous.end <= current.start
