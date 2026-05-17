package mmis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvScheduleWriter {
    public void writeSchedule(Path outputPath, List<ScheduledJob> scheduledJobs) throws IOException {
        List<String> rows = new ArrayList<>();
        rows.add("machine_id,job_id,start,end");
        for (ScheduledJob scheduledJob : scheduledJobs) {
            Job job = scheduledJob.getJob();
            rows.add(String.format("%s,%s,%d,%d",
                scheduledJob.getMachineId(),
                job.getJobId(),
                job.getStart(),
                job.getEnd()));
        }
        Files.createDirectories(outputPath.getParent());
        Files.write(outputPath, rows);
    }

    public void writeUnassigned(Path outputPath, List<Job> unassignedJobs) throws IOException {
        List<String> rows = new ArrayList<>();
        rows.add("job_id,start,end,reason");
        for (Job job : unassignedJobs) {
            rows.add(String.format("%s,%d,%d,%s",
                job.getJobId(),
                job.getStart(),
                job.getEnd(),
                "overlap_no_machine_available"));
        }
        Files.createDirectories(outputPath.getParent());
        Files.write(outputPath, rows);
    }
}
