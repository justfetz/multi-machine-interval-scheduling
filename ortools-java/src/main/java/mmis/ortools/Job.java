package mmis.ortools;

public record Job(String jobId, int start, int end) {
    public Job {
        if (end <= start) {
            throw new IllegalArgumentException("Job end must be greater than start.");
        }
    }
}
