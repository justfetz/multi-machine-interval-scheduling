package mmis.tests;

import mmis.CsvJobReader;
import mmis.GreedyScheduler;
import mmis.Job;
import mmis.ScheduledJob;
import mmis.SolverResult;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestGreedyScheduler {
    public static void main(String[] args) throws Exception {
        testSampleInstanceCounts();
        testNoOverlapPerMachine();
        testInvalidJobsRejected();
        System.out.println("Java tests passed.");
    }

    private static void testSampleInstanceCounts() throws Exception {
        CsvJobReader reader = new CsvJobReader();
        List<Job> jobs = reader.read(Path.of("..", "input", "sample_jobs.csv"));
        GreedyScheduler scheduler = new GreedyScheduler();
        SolverResult result = scheduler.solve(jobs, 3);

        assertEquals(8, result.getScheduledJobs().size(), "scheduled job count");
        assertEquals(3, result.getUnassignedJobs().size(), "unassigned job count");
    }

    private static void testNoOverlapPerMachine() throws Exception {
        CsvJobReader reader = new CsvJobReader();
        List<Job> jobs = reader.read(Path.of("..", "input", "sample_jobs_medium.csv"));
        GreedyScheduler scheduler = new GreedyScheduler();
        SolverResult result = scheduler.solve(jobs, 3);

        Map<String, List<ScheduledJob>> jobsByMachine = new HashMap<>();
        for (ScheduledJob scheduledJob : result.getScheduledJobs()) {
            jobsByMachine.computeIfAbsent(scheduledJob.getMachineId(), ignored -> new ArrayList<>()).add(scheduledJob);
        }

        for (List<ScheduledJob> machineJobs : jobsByMachine.values()) {
            machineJobs.sort(Comparator.comparingInt(item -> item.getJob().getStart()));
            for (int index = 1; index < machineJobs.size(); index++) {
                Job previous = machineJobs.get(index - 1).getJob();
                Job current = machineJobs.get(index).getJob();
                assertTrue(previous.getEnd() <= current.getStart(),
                    "overlap detected between " + previous.getJobId() + " and " + current.getJobId());
            }
        }
    }

    private static void testInvalidJobsRejected() {
        boolean threw = false;
        try {
            new Job("bad-job", 10, 10);
        } catch (IllegalArgumentException expected) {
            threw = true;
        }
        assertTrue(threw, "invalid job should raise IllegalArgumentException");
    }

    private static void assertEquals(int expected, int actual, String label) {
        if (expected != actual) {
            throw new AssertionError("Expected " + label + " to be " + expected + " but was " + actual);
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
