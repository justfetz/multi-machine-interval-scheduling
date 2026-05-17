from dataclasses import dataclass


@dataclass(frozen=True)
class Job:
    job_id: str
    start: int
    end: int


@dataclass(frozen=True)
class ScheduledJob:
    machine_id: str
    job: Job
