package task2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.nio.file.*;


public class Task2Main {
    public static void runTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть шлях до директорії:");
        String directoryPath = scanner.nextLine();

        Path directory = Paths.get(directoryPath);
        if (!Files.isDirectory(directory)) {
            System.out.println("Помилка: Вказаний шлях не є директорією.");
            return;
        }

        // Work stealing
        try (ForkJoinPool pool = new ForkJoinPool()) {
            FileAnalysisTask task = new FileAnalysisTask(directory);
            System.out.println("Work stealing результати:");
            String result = pool.invoke(task);
            System.out.println(result);
        }
    }
}
