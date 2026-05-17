import csv
from pathlib import Path

from .models import Job, ScheduledJob


def read_jobs(input_path: Path) -> list[Job]:
    with input_path.open("r", newline="", encoding="utf-8") as handle:
        reader = csv.DictReader(handle)
        return [
            Job(
                job_id=row["job_id"],
                start=int(row["start"]),
                end=int(row["end"]),
            )
            for row in reader
        ]


def write_schedule(output_path: Path, scheduled_jobs: list[ScheduledJob]) -> None:
    output_path.parent.mkdir(parents=True, exist_ok=True)
    with output_path.open("w", newline="", encoding="utf-8") as handle:
        writer = csv.writer(handle)
        writer.writerow(["machine_id", "job_id", "start", "end"])
        for scheduled in scheduled_jobs:
            writer.writerow([
                scheduled.machine_id,
                scheduled.job.job_id,
                scheduled.job.start,
                scheduled.job.end,
            ])


def write_unassigned(output_path: Path, jobs: list[Job]) -> None:
    output_path.parent.mkdir(parents=True, exist_ok=True)
    with output_path.open("w", newline="", encoding="utf-8") as handle:
        writer = csv.writer(handle)
        writer.writerow(["job_id", "start", "end", "reason"])
        for job in jobs:
            writer.writerow([job.job_id, job.start, job.end, "overlap_no_machine_available"])
