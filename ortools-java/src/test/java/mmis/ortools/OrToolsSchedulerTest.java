package mmis.ortools;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrToolsSchedulerTest {
    @Test
    void sampleInstanceSchedulesEightJobs() throws Exception {
        List<Job> jobs = new CsvJobReader().read(Path.of("..", "input", "sample_jobs.csv"));
        OrToolsScheduler.Result result = new OrToolsScheduler().solve(jobs, 3);

        assertEquals(8, result.scheduledJobs().size());
        assertEquals(3, result.unassignedJobs().size());
    }

    @Test
    void machineAssignmentsDoNotOverlap() throws Exception {
        List<Job> jobs = new CsvJobReader().read(Path.of("..", "input", "sample_jobs_medium.csv"));
        OrToolsScheduler.Result result = new OrToolsScheduler().solve(jobs, 3);

        Map<String, List<ScheduledJob>> jobsByMachine = new HashMap<>();
        for (ScheduledJob scheduledJob : result.scheduledJobs()) {
            jobsByMachine.computeIfAbsent(scheduledJob.machineId(), ignored -> new java.util.ArrayList<>()).add(scheduledJob);
        }

        for (List<ScheduledJob> machineJobs : jobsByMachine.values()) {
            machineJobs.sort(java.util.Comparator.comparingInt(item -> item.job().start()));
            for (int index = 1; index < machineJobs.size(); index++) {
                Job previous = machineJobs.get(index - 1).job();
                Job current = machineJobs.get(index).job();
                assertTrue(previous.end() <= current.start());
            }
        }
    }
}
