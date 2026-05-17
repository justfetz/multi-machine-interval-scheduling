package mmis;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GreedyScheduler {
    public SolverResult solve(List<Job> jobs, int machineCount) {
        List<Job> sortedJobs = new ArrayList<>(jobs);
        sortedJobs.sort(Comparator.comparingInt(Job::getEnd).thenComparingInt(Job::getStart));

        List<MachineSchedule> machines = new ArrayList<>();
        for (int index = 0; index < machineCount; index++) {
            machines.add(new MachineSchedule("M" + (index + 1)));
        }

        List<ScheduledJob> scheduled = new ArrayList<>();
        List<Job> unassigned = new ArrayList<>();

        for (Job job : sortedJobs) {
            boolean assigned = false;
            for (MachineSchedule machine : machines) {
                if (machine.canAssign(job)) {
                    machine.assign(job);
                    scheduled.add(new ScheduledJob(machine.getMachineId(), job));
                    assigned = true;
                    break;
                }
            }
            if (!assigned) {
                unassigned.add(job);
            }
        }

        scheduled.sort(Comparator.comparing(ScheduledJob::getMachineId).thenComparingInt(item -> item.getJob().getStart()));
        return new SolverResult(scheduled, unassigned);
    }
}
