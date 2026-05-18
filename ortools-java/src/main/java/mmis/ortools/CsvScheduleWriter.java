package mmis.ortools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvScheduleWriter {
    public void writeSchedule(Path outputPath, List<ScheduledJob> scheduledJobs) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("machine_id,job_id,start,end,duration");
        for (ScheduledJob scheduledJob : scheduledJobs) {
            Job job = scheduledJob.job();
            lines.add(String.format("%s,%s,%d,%d,%d",
                scheduledJob.machineId(), job.jobId(), job.start(), job.end(), job.end() - job.start()));
        }
        Files.write(outputPath, lines);
    }

    public void writeUnassigned(Path outputPath, List<Job> unassignedJobs) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("job_id,start,end,duration");
        for (Job job : unassignedJobs) {
            lines.add(String.format("%s,%d,%d,%d", job.jobId(), job.start(), job.end(), job.end() - job.start()));
        }
        Files.write(outputPath, lines);
    }
}
