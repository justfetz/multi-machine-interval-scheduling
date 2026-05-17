package mmis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvJobReader {
    public List<Job> read(Path inputPath) throws IOException {
        List<String> lines = Files.readAllLines(inputPath);
        List<Job> jobs = new ArrayList<>();

        for (int index = 1; index < lines.size(); index++) {
            String line = lines.get(index).trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] tokens = line.split(",");
            jobs.add(new Job(tokens[0].trim(), Integer.parseInt(tokens[1].trim()), Integer.parseInt(tokens[2].trim())));
        }

        return jobs;
    }
}
