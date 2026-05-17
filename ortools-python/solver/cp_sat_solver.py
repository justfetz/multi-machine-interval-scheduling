from ortools.sat.python import cp_model

from .models import Job, ScheduledJob


def overlaps(left: Job, right: Job) -> bool:
    return left.start < right.end and right.start < left.end


def solve_cp_sat(jobs: list[Job], machine_count: int) -> tuple[list[ScheduledJob], list[Job]]:
    model = cp_model.CpModel()
    assignment: dict[tuple[int, int], cp_model.IntVar] = {}

    for job_index, _job in enumerate(jobs):
        for machine_index in range(machine_count):
            assignment[(job_index, machine_index)] = model.NewBoolVar(f"job_{job_index}_machine_{machine_index}")

    for job_index, _job in enumerate(jobs):
        model.Add(sum(assignment[(job_index, machine_index)] for machine_index in range(machine_count)) <= 1)

    for machine_index in range(machine_count):
        for left_index in range(len(jobs)):
            for right_index in range(left_index + 1, len(jobs)):
                if overlaps(jobs[left_index], jobs[right_index]):
                    model.Add(
                        assignment[(left_index, machine_index)] + assignment[(right_index, machine_index)] <= 1
                    )

    model.Maximize(
        sum(assignment[(job_index, machine_index)] for job_index in range(len(jobs)) for machine_index in range(machine_count))
    )

    solver = cp_model.CpSolver()
    solver.parameters.max_time_in_seconds = 10
    solver.parameters.num_search_workers = 8
    status = solver.Solve(model)

    if status not in (cp_model.OPTIMAL, cp_model.FEASIBLE):
        raise RuntimeError("No feasible solution returned by OR-Tools.")

    scheduled: list[ScheduledJob] = []
    unassigned: list[Job] = []

    for job_index, job in enumerate(jobs):
        assigned_machine = None
        for machine_index in range(machine_count):
            if solver.Value(assignment[(job_index, machine_index)]) == 1:
                assigned_machine = machine_index
                break
        if assigned_machine is None:
            unassigned.append(job)
        else:
            scheduled.append(ScheduledJob(machine_id=f"M{assigned_machine + 1}", job=job))

    scheduled.sort(key=lambda item: (item.machine_id, item.job.start, item.job.end, item.job.job_id))
    return scheduled, unassigned
