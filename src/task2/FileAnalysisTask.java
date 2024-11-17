package task2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.RecursiveTask;

public class FileAnalysisTask extends RecursiveTask<String> {
    private final Path directory;

    public FileAnalysisTask(Path directory) {
        this.directory = directory;
    }

    @Override
    protected String compute() {
        StringBuilder result = new StringBuilder();

        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (Files.isRegularFile(file) && file.toString().endsWith(".txt")) {
                        long charCount = Files.lines(file).mapToLong(String::length).sum();
                        result.append(file.getFileName()).append(": ").append(charCount).append(" символів\n");
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
