package mmis;

import java.util.ArrayList;
import java.util.List;

public class MachineSchedule {
    private final String machineId;
    private final List<Job> assignedJobs;

    public MachineSchedule(String machineId) {
        this.machineId = machineId;
        this.assignedJobs = new ArrayList<>();
    }

    public String getMachineId() {
        return machineId;
    }

    public List<Job> getAssignedJobs() {
        return assignedJobs;
    }

    public boolean canAssign(Job job) {
        if (assignedJobs.isEmpty()) {
            return true;
        }
        Job last = assignedJobs.get(assignedJobs.size() - 1);
        return last.getEnd() <= job.getStart();
    }

    public void assign(Job job) {
        assignedJobs.add(job);
    }
}
