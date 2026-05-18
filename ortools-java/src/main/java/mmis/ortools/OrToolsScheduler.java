package mmis.ortools;

import com.google.ortools.Loader;
import com.google.ortools.sat.BoolVar;
import com.google.ortools.sat.CpModel;
import com.google.ortools.sat.CpSolver;
import com.google.ortools.sat.CpSolverStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrToolsScheduler {
    public Result solve(List<Job> jobs, int machineCount) {
        Loader.loadNativeLibraries();

        CpModel model = new CpModel();
        BoolVar[][] assignment = new BoolVar[jobs.size()][machineCount];

        for (int jobIndex = 0; jobIndex < jobs.size(); jobIndex++) {
            for (int machineIndex = 0; machineIndex < machineCount; machineIndex++) {
                assignment[jobIndex][machineIndex] = model.newBoolVar("job_" + jobIndex + "_machine_" + machineIndex);
            }
        }

        for (int jobIndex = 0; jobIndex < jobs.size(); jobIndex++) {
            List<BoolVar> vars = new ArrayList<>();
            for (int machineIndex = 0; machineIndex < machineCount; machineIndex++) {
                vars.add(assignment[jobIndex][machineIndex]);
            }
            model.addLessOrEqual(com.google.ortools.sat.LinearExpr.sum(vars.toArray(new BoolVar[0])), 1);
        }

        for (int machineIndex = 0; machineIndex < machineCount; machineIndex++) {
            for (int left = 0; left < jobs.size(); left++) {
                for (int right = left + 1; right < jobs.size(); right++) {
                    if (overlaps(jobs.get(left), jobs.get(right))) {
                        model.addLessOrEqual(com.google.ortools.sat.LinearExpr.sum(
                            new BoolVar[] {assignment[left][machineIndex], assignment[right][machineIndex]}), 1);
                    }
                }
            }
        }

        List<BoolVar> objectiveVars = new ArrayList<>();
        for (int jobIndex = 0; jobIndex < jobs.size(); jobIndex++) {
            for (int machineIndex = 0; machineIndex < machineCount; machineIndex++) {
                objectiveVars.add(assignment[jobIndex][machineIndex]);
            }
        }
        model.maximize(com.google.ortools.sat.LinearExpr.sum(objectiveVars.toArray(new BoolVar[0])));

        CpSolver solver = new CpSolver();
        solver.getParameters().setMaxTimeInSeconds(10.0);
        solver.getParameters().setNumSearchWorkers(8);
        CpSolverStatus status = solver.solve(model);
        if (status != CpSolverStatus.OPTIMAL && status != CpSolverStatus.FEASIBLE) {
            throw new IllegalStateException("No feasible solution returned by OR-Tools.");
        }

        List<ScheduledJob> scheduled = new ArrayList<>();
        List<Job> unassigned = new ArrayList<>();
        for (int jobIndex = 0; jobIndex < jobs.size(); jobIndex++) {
            Integer assignedMachine = null;
            for (int machineIndex = 0; machineIndex < machineCount; machineIndex++) {
                if (solver.booleanValue(assignment[jobIndex][machineIndex])) {
                    assignedMachine = machineIndex;
                    break;
                }
            }
            if (assignedMachine == null) {
                unassigned.add(jobs.get(jobIndex));
            } else {
                scheduled.add(new ScheduledJob("M" + (assignedMachine + 1), jobs.get(jobIndex)));
            }
        }

        scheduled.sort(Comparator.comparing(ScheduledJob::machineId)
            .thenComparingInt(item -> item.job().start())
            .thenComparingInt(item -> item.job().end())
            .thenComparing(item -> item.job().jobId()));

        return new Result(scheduled, unassigned);
    }

    private boolean overlaps(Job left, Job right) {
        return left.start() < right.end() && right.start() < left.end();
    }

    public record Result(List<ScheduledJob> scheduledJobs, List<Job> unassignedJobs) {}
}
