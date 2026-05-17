package mmis;

public class ScheduledJob {
    private final String machineId;
    private final Job job;

    public ScheduledJob(String machineId, Job job) {
        this.machineId = machineId;
        this.job = job;
    }

    public String getMachineId() {
        return machineId;
    }

    public Job getJob() {
        return job;
    }
}
