package mmis.ortools;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            System.out.println("Usage: java mmis.ortools.Main <input.csv> <machine-count> <schedule-output.csv> <unassigned-output.csv>");
            return;
        }

        Path inputPath = Path.of(args[0]);
        int machineCount = Integer.parseInt(args[1]);
        Path scheduleOutputPath = Path.of(args[2]);
        Path unassignedOutputPath = Path.of(args[3]);

        List<Job> jobs = new CsvJobReader().read(inputPath);
        OrToolsScheduler.Result result = new OrToolsScheduler().solve(jobs, machineCount);

        CsvScheduleWriter writer = new CsvScheduleWriter();
        writer.writeSchedule(scheduleOutputPath, result.scheduledJobs());
        writer.writeUnassigned(unassignedOutputPath, result.unassignedJobs());

        System.out.printf("Scheduled %d jobs and left %d unassigned.%n",
            result.scheduledJobs().size(), result.unassignedJobs().size());
    }
}
