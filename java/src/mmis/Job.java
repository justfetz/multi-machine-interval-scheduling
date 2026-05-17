package mmis;

public class Job {
    private final String jobId;
    private final int start;
    private final int end;

    public Job(String jobId, int start, int end) {
        if (end <= start) {
            throw new IllegalArgumentException("Job end must be greater than start.");
        }
        this.jobId = jobId;
        this.start = start;
        this.end = end;
    }

    public String getJobId() {
        return jobId;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getDuration() {
        return end - start;
    }
}
