from .models import Job, ScheduledJob


def solve_greedy(jobs: list[Job], machine_count: int) -> tuple[list[ScheduledJob], list[Job]]:
    machine_last_end = [None] * machine_count
    sorted_jobs = sorted(jobs, key=lambda job: (job.end, job.start, job.job_id))
    scheduled: list[ScheduledJob] = []
    unassigned: list[Job] = []

    for job in sorted_jobs:
        assigned = False
        for machine_index in range(machine_count):
            last_end = machine_last_end[machine_index]
            if last_end is None or last_end <= job.start:
                machine_last_end[machine_index] = job.end
                scheduled.append(ScheduledJob(machine_id=f"M{machine_index + 1}", job=job))
                assigned = True
                break
        if not assigned:
            unassigned.append(job)

    scheduled.sort(key=lambda item: (item.machine_id, item.job.start, item.job.end, item.job.job_id))
    return scheduled, unassigned
