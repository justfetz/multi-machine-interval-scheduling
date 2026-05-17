package mmis;

import java.util.List;

public class SolverResult {
    private final List<ScheduledJob> scheduledJobs;
    private final List<Job> unassignedJobs;

    public SolverResult(List<ScheduledJob> scheduledJobs, List<Job> unassignedJobs) {
        this.scheduledJobs = scheduledJobs;
        this.unassignedJobs = unassignedJobs;
    }

    public List<ScheduledJob> getScheduledJobs() {
        return scheduledJobs;
    }

    public List<Job> getUnassignedJobs() {
        return unassignedJobs;
    }
}
